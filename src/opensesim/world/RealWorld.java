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
import opensesim.util.scheduler.EventListener;

/**
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class RealWorld implements World {

    private GodWorld godworld;

    protected RealWorld(GodWorld godworld) {
        this.godworld = godworld;
    }

    @Override
    public Collection<AbstractAsset> getAssetCollection() {
        return godworld.getAssetCollection();
    }

    @Override
    public AbstractAsset getAssetBySymbol(String symbol) {
        return godworld.getAssetBySymbol(symbol);
    }

    @Override
    public Collection<Exchange> getExchangeCollection() {
        return godworld.getExchangeCollection();
    }

    @Override
    public Collection<Trader> getTradersCollection() {
        return godworld.getTradersCollection();
    }

    @Override
    public void schedule(EventListener listener, long t) {
        godworld.schedule(listener, t);
    }

    @Override
    public float randNextFloat(float min, float max) {
        return godworld.randNextFloat(min, max);
    }

    @Override
    public long currentTimeMillis() {
        return godworld.currentTimeMillis();
    }

    @Override
    public Exchange getDefaultExchange() {
        return godworld.getDefaultExchange();
    }

    @Override
    public AssetPair getDefaultAssetPair() {
        return godworld.getDefaultAssetPair();
    }

}
