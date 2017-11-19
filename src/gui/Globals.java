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
package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.json.JSONArray;
import org.json.JSONObject;
import sesim.AutoTraderInterface;
import sesim.AutoTraderLoader;
import sesim.SeSimClassLoader;

/**
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class Globals {

    public static final String SESIM_FILEEXTENSION = "sesim";
    public static final Double SESIM_FILEVERSION = 0.1;

    public static final String SESIM_APPTITLE = "SeSim - Stock Exchange Simulator";

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
    static final String DEVELSTATUS = "devel_status";
    public static final String GODMODE = "godmode";

    static public sesim.Exchange se;

    /**
     * Defines keys for preferences
     */
    public static final class PrefKeys {

        public static String WORKDIR = "workdir";
        public static final String CURRENTFILE = "currentfile";

        public static final String SESIMVERSION = "version";
        public static final String STRATEGIES = "strategies";
        public static final String TRADERS = "traders";

    }

    static public Preferences prefs;

    public static class CfgStrings {

        public static final String GODMODE = "godmode";
    }

    public static String DEFAULT_EXCHANGE_CFG
            = "{"
            + "  money_decimals: 2,"
            + "  shares_decimals: 0"
            + "}";

    //CfgStrings 
    static void setLookAndFeel(String selected) {

        try {
            String look = "com.seaglasslookandfeel.SeaGlassLookAndFeel";
            Class.forName(look);
            UIManager.installLookAndFeel("Sea Glass", look);
        } catch (ClassNotFoundException e) {
        }

        UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo lafInfo1 : lafInfo) {
            if (lafInfo1.getName().equals(selected)) {
                String lafClassName = lafInfo1.getClassName();
                try {
                    UIManager.setLookAndFeel(lafClassName);
                    //  UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
                    break;
                } catch (Exception e) {

                }
            }
        }
    }

    static AutoTraderLoader tloader;

    static void initGlobals() {
        ArrayList pathlist = new ArrayList<>();
        String default_path = new java.io.File(SeSimApplication.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath()).toString();

        pathlist.add(default_path);
        
        SeSimClassLoader cl = new SeSimClassLoader(pathlist);
        cl.getInstalledClasses(AutoTraderInterface.class);
        
        
        
        tloader = new AutoTraderLoader(pathlist);
    }

    static public final Logger LOGGER = Logger.getLogger("com.cauwersin.sesim");

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

        JSONObject sobj = new JSONObject();

        JSONArray traders = getTraders();
        JSONObject strategies = getStrategies();

        sobj.put(PrefKeys.SESIMVERSION, SESIM_FILEVERSION);
        sobj.put(PrefKeys.STRATEGIES, strategies);
        sobj.put(PrefKeys.TRADERS, traders);

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

}
