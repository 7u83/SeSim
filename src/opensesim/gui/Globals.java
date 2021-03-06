/*
 * Copyright (c) 2017, 7u83 <7u83@mail.ru>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package opensesim.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import opensesim.world.AbstractAsset;
import opensesim.world.RealWorld;
import opensesim.gui.AssetEditor.AssetEditorPanel;
import org.json.JSONArray;
import org.json.JSONObject;
import opensesim.old_sesim.AutoTraderLoader;
import opensesim.old_sesim.IndicatorLoader;
import opensesim.util.XClassLoader.ClassCache;
import opensesim.world.GodWorld;
import opensesim.world.Trader;

/**
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class Globals {

    public static RealWorld world;

    public static final String SESIM_FILEEXTENSION = "sesim";
    public static final Double SESIM_FILEVERSION = 0.1;

    public static final String SESIM_APPTITLE = "OpenSeSim - Stock Exchange Simulator";

    public interface CfgListener {

        void cfgChanged();
    }

    static ArrayList<CfgListener> cfg_listeners = new ArrayList<>();

    public static void notifyCfgListeners() {
        for (CfgListener l : cfg_listeners) {
            l.cfgChanged();
        }
    }

    public static void addCfgListener(CfgListener l) {
        cfg_listeners.add(l);
    }

    public static JFrame frame;

    // static final String STRATEGYPREFS = "Strategies";
    // static final String TRADERPREFS = "Traders";
    public static final String DEVELSTATUS = "devel_status";
    public static final String GODMODE = "godmode";

    static public opensesim.old_sesim.Exchange se;

    /**
     * Defines keys for preferences
     */
    public static final class PrefKeys {

        public static final String XCLASSPATH = "xclasspath";
        public static final String LAF = "laf";

        public static String WORKDIR = "workdir";
        public static final String CURRENTFILE = "currentfile";

        public static final String SESIMVERSION = "version";
        public static final String STRATEGIES = "strategies";
        public static final String TRADERS = "traders";
        public static final String ASSETS = "assets";
        public static final String EXCHANGES = "exchanges";

    }

    public static final class MAX {

        public static final int SYMLEN = 16;
        public static final int NAMELEN = 64;
    }

    /**
     * Preferences
     */
    static public Preferences prefs;

    public static class CfgStrings {

        public static final String GODMODE = "godmode";

    }

    public static String DEFAULT_EXCHANGE_CFG
            = "{"
            + "  money_decimals: 2,"
            + "  shares_decimals: 0"
            + "}";

    /**
     *
     * @param selected
     */
    public static void setLookAndFeel(String selected) {

        ClassLoader old;
        old = Globals.setXClassLoader();
        Logger.getLogger(Globals.class.getName()).log(Level.SEVERE, "Setting LAF:" + selected);
        UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo lafInfo1 : lafInfo) {
            if (lafInfo1.getName().equals(selected)) {
                String lafClassName = lafInfo1.getClassName();
                try {
                    Logger.getLogger(Globals.class.getName()).log(Level.SEVERE, "Setting LAF:" + lafClassName);
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    JDialog.setDefaultLookAndFeelDecorated(true);

                    UIManager.setLookAndFeel(lafClassName);
                    break;
                } catch (Exception e) {
                    Logger.getLogger(Globals.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        Globals.unsetXClassLoader(old);
    }

    static AutoTraderLoader tloader;
    static IndicatorLoader iloader;

    static ArrayList default_pathlist = new ArrayList<>();

    static ArrayList<URL> urllist;

    static public void updateUrlList() {
        urllist = new ArrayList<>();
        URL url;
        try {
            url = new java.io.File(Globals.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath()).toURI().toURL();
            urllist.add(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Globals.class.getName()).log(Level.SEVERE, null, ex);
        }

        String xpath;
        xpath = prefs.get(Globals.PrefKeys.XCLASSPATH, null);

        urllist.addAll(opensesim.util.XClassLoader.getXPathUrlList(xpath));
    }

    static ClassLoader setXClassLoader() {
        ClassLoader old_classloader = Thread.currentThread().getContextClassLoader();
        if (urllist == null) {
            return old_classloader;
        }
        URL[] urls = urllist.toArray(new URL[urllist.size()]);
        URLClassLoader cl;
        cl = new URLClassLoader(urls, old_classloader);
        Thread.currentThread().setContextClassLoader(cl);
        return old_classloader;
    }

    static void unsetXClassLoader(ClassLoader old_classloader) {
        Thread.currentThread().setContextClassLoader(old_classloader);

    }

    static public ArrayList<Class<AbstractAsset>> getAvailableAssetsTypes(boolean sort) {

        // if class_cache is not initialized return an empty list
        if (class_cache == null) {
            return new ArrayList<>();
        }

        Collection<Class> asset_types_raw;
        asset_types_raw = class_cache.getClassCollection(AbstractAsset.class);

        ArrayList<Class<AbstractAsset>> asset_types = new ArrayList<>();
        asset_types_raw.forEach((a) -> {
            asset_types.add(a);
        });

        if (!sort) {
            return asset_types;
        }

        asset_types.sort(new Comparator<Class<AbstractAsset>>() {
            @Override
            public int compare(Class<AbstractAsset> o1, Class<AbstractAsset> o2) {
                String t1, t2;
                t1 = GodWorld.getTypeName(o1);
                t2 = GodWorld.getTypeName(o2);
                return t1.compareToIgnoreCase(t2);
            }

        });

        return asset_types;
    }

    static public ArrayList<Class<AbstractAsset>> getAvailableAssetsTypes() {
        return getAvailableAssetsTypes(false);
    }

    static public Class getClassByName(String name) {
        return class_cache.getClassByName(name);
    }

    static private void installLookAndFeels() {
        Collection<Class> lafs;
        lafs = class_cache.getClassCollection(LookAndFeel.class);
        if (lafs == null) {
            return;
        }

        ClassLoader currentThreadClassLoader
                = Thread.currentThread().getContextClassLoader();
        URL[] urls = urllist.toArray(new URL[urllist.size()]);
        URLClassLoader cl;
        cl = new URLClassLoader(urls, currentThreadClassLoader);
        Thread.currentThread().setContextClassLoader(cl);

        for (Class<?> cls : lafs) {

            Class<LookAndFeel> lafc;
            lafc = (Class<LookAndFeel>) cls;
            LookAndFeel laf;

            try {
                laf = lafc.getConstructor().newInstance();
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                //Logger.getLogger(Globals.class.getName()).log(Level.SEVERE, null, ex);
                continue;
            }

            UIManager.installLookAndFeel(laf.getName() + " - " + laf.getDescription(), lafc.getName());

        }

        Thread.currentThread().setContextClassLoader(currentThreadClassLoader);

    }

    private static ClassCache class_cache;

    /**
     * Initialize Globals object.
     *
     * This function initializes a Preferences object where preferences are
     * stored. Furthermore a search trough all class paths is performed to find
     * available LookAndFeels, Traders and Assets.
     *
     * @param c The class which uses this Globals object
     */
    static void initGlobals(Class<?> c) {

        prefs = Preferences.userNodeForPackage(c);

        // initialize urllist used by class loader
        updateUrlList();

        class_cache = new ClassCache(urllist, new Class[]{
            LookAndFeel.class,
            AbstractAsset.class,
            Trader.class
        });

        installLookAndFeels();

    }

    static public final Logger LOGGER = Logger.getLogger("com.cauwersin.sesim");

    static public final JSONObject getAssets() {
        String assets_json = prefs.get(PrefKeys.ASSETS, "{}");
        return new JSONObject(assets_json);
    }

    static public final void putAssets(JSONObject assets) {
        prefs.put(PrefKeys.ASSETS, assets.toString());
    }

    static public final JSONObject getExchanges() {
        String json = prefs.get(PrefKeys.EXCHANGES, "{}");
        return new JSONObject(json);
    }

    static public final void putExchanges(JSONObject e) {
        prefs.put(PrefKeys.EXCHANGES, e.toString());
    }

    static public final JSONArray getTraders() {

        String traders_json = Globals.prefs.get(PrefKeys.TRADERS, "[]");
        JSONArray traders = new JSONArray(traders_json);
        return traders;
    }

    static public final JSONObject getStrategies() {
        String cfglist = Globals.prefs.get(PrefKeys.STRATEGIES, "{}");
        JSONObject cfgs = new JSONObject(cfglist);
        return cfgs;
    }

    static public final void putStrategies(JSONObject strategies) {
        Globals.prefs.put(Globals.PrefKeys.STRATEGIES, strategies.toString());
    }

    static public final void putTraders(JSONArray traders) {
        Globals.prefs.put(Globals.PrefKeys.TRADERS, traders.toString());
    }

    static public JSONObject getStrategy(String name) {
        return getStrategies().getJSONObject(name);
    }

    static public void getStrategiesIntoComboBox(JComboBox comboBox) {
        TreeMap stm = getStrategiesAsTreeMap();
        comboBox.removeAllItems();

        Iterator<String> i = stm.keySet().iterator();
        while (i.hasNext()) {
            comboBox.addItem(i.next());
        }

    }

    static public TreeMap getStrategiesAsTreeMap() {
        TreeMap strategies = new TreeMap();
        JSONObject cfgs = Globals.getStrategies();

        Iterator<String> i = cfgs.keys();
        while (i.hasNext()) {
            String k = i.next();
            JSONObject o = cfgs.getJSONObject(k);
            strategies.put(k, o);
        }
        return strategies;
    }

    static public final void saveStrategy(String name, JSONObject cfg) {
        JSONObject cfgs = getStrategies();
        cfgs.put(name, cfg);
        prefs.put(PrefKeys.STRATEGIES, cfgs.toString());
    }

    public static void saveFile(File f) throws FileNotFoundException {

        /*       JSONObject sobj = new JSONObject();

        JSONArray traders = getTraders();
        JSONObject strategies = getStrategies();

        sobj.put(PrefKeys.SESIMVERSION, SESIM_FILEVERSION);
        sobj.put(PrefKeys.STRATEGIES, strategies);
        sobj.put(PrefKeys.TRADERS, traders);
         */
        JSONObject sobj = Globals.getWorld();

        PrintWriter out;
        out = new PrintWriter(f.getAbsolutePath());
        out.print(sobj.toString(4));
        out.close();

    }

    public static void loadString(String s) throws IOException {
        JSONObject sobj = new JSONObject(s);

        Double version = sobj.getDouble(PrefKeys.SESIMVERSION);
        if (version > SESIM_FILEVERSION) {
            throw new IOException("File has wrong version.");
        }

        JSONArray traders = sobj.getJSONArray(PrefKeys.TRADERS);
        JSONObject strategies = sobj.getJSONObject(PrefKeys.STRATEGIES);

        putStrategies(strategies);
        putTraders(traders);

    }

    public static void clearAll() {
        putStrategies(new JSONObject());
        putTraders(new JSONArray());
    }

    public static void loadFile(File f) throws IOException {

        f.getAbsoluteFile();
        String s;
        s = new String(Files.readAllBytes(f.toPath()));

        loadString(s);

    }

    public static JSONObject getWorld() {
        JSONObject cfg = new JSONObject();
        cfg.put(GodWorld.JKEYS.ASSETS, getAssets());
        cfg.put(GodWorld.JKEYS.EXCHANGES, getExchanges());
        return cfg;
    }

}
