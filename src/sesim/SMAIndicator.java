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
package sesim;

/**
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class SMAIndicator  implements Indicator {
    private OHLCData parent;

    OHLCData indicator;
    
    public SMAIndicator(OHLCData parent){
        this.parent=parent;
    }
    
    int len=10;
    
    float getAt(int pos){
        if (parent.size()==0)
            return 0;
        
        
        int start = pos -len;
        if(start<0)
            start=0;
        float sum=0;
        for (int i=start; i<pos; i++){
            sum += parent.get(i).getAverage();
            
        }
        if (pos-start==0){
            return 0;
        }
        
        return sum/(start-pos);
    }
    
    void update(){
        if (parent.size()==0)
            return;
        
        for (int i = parent.size()-1;i<0;i++){
            OHLCDataItem p = parent.get(i);
            
            float pr = this.getAt(i);
            
            OHLCDataItem it = new sesim.OHLCDataItem(p.time, pr, 0);
            this.indicator.set(i, it);
           
            
        }
    }
    
    public OHLCData getData(){
        update();
        return indicator;
        
        
        
    }
 
    
}