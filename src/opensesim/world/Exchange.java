/*
 * Copyright (c) 2018, 7u83 <7u83@mail.ru>
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
package opensesim.world;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;
import opensesim.world.RealWorld;
import opensesim.sesim.interfaces.Configurable;
import opensesim.sesim.interfaces.GetJson;
import org.json.JSONObject;

/**
 *
 * @author 7u83
 */
public class Exchange implements Configurable, GetJson {

    private World world;
    private String name;
    private String symbol;

    public void setName(String name) {
        this.name = name;
    }

    private final HashMap<AssetPair, TradingAPI> asset_pairs = new HashMap<>();

    Exchange(World world, String symbol) {

        this.world = world;
        this.symbol = symbol;
    }

    Exchange(World world, JSONObject cfg) {
        this.world = world;
        this.name = cfg.optString("name", "Sesim");
        this.symbol = cfg.optString("symbol");

    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    private HashSet<Account> accounts = new HashSet<>();

    Account createAccount() {
        Account a = new Account(this);
        accounts.add(a);
        return a;
    }

    public Order createOrder(Account account, AssetPair pair, Order.Type type, double volume, double limit) {

//        Order o = new Order(world,account,pair,type,volume,limit);
        return null;
    }

    @Override
    public JSONObject getConfig() {
        JSONObject cfg = new JSONObject();
        cfg.put("symbol", getSymbol());
        cfg.put("name", getName());
        return cfg;

    }

    @Override
    public void putConfig(JSONObject cfg) {

    }

    @Override
    public JSONObject getJson() {
        JSONObject cfg = new JSONObject();
        cfg.put("symbol", getSymbol());
        cfg.put("name", getName());
        return cfg;
    }

    class TradingEnv implements TradingAPI {

        protected HashMap<Order.Type, SortedSet<Order>> order_books;

        TradingEnv() {
            reset();
        }

        protected final void reset() {
            order_books = new HashMap();

            // Create an order book for each order type
            for (Order.Type type : Order.Type.values()) {
                order_books.put(type, new TreeSet<>());
            }

            //  quoteHistory = new TreeSet();
            //  ohlc_data = new HashMap();
        }

        @Override
        public Order createOrder(Account account, Order.Type type, double volume, double limit) {

            return null;
        }

    }

    private TradingAPI add(AssetPair p) {
        TradingEnv e = new TradingEnv();
        asset_pairs.put(p, e);
        return e;
    }

    public TradingAPI getAPI(AssetPair pair) {
        TradingAPI a = asset_pairs.get(pair);
        if (a == null) {
            return add(pair);
        }
        return a;
    }
}
