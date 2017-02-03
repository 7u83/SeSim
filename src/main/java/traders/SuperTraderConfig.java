/*
 * Copyright (c) 2017, tobias
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
package traders;

import javax.swing.JPanel;
import org.json.JSONObject;
import sesim.AutoTrader;
import sesim.AutoTraderConfig;
import sesim.AutoTraderGui;
import sesim.Exchange;

/**
 *
 * @author tobias
 */
public class SuperTraderConfig implements AutoTraderConfig{

    @Override
    public AutoTrader createTrader(Exchange se, JSONObject cfg, double money, double shares) {
        return null;
    }

    @Override
    public String getDisplayName() {
        return "SuperTrader";
    }
    
    
    public SuperTraderConfig(){
        
    }

    @Override
    public AutoTraderGui getGui() {
        return null;
    }

    @Override
    public JSONObject getConfig() {
        return null;
    }

    @Override
    public void putConfig(JSONObject cfg) {
        
    }

    @Override
    public boolean getDevelStatus() {
        return true;
    }
    
}