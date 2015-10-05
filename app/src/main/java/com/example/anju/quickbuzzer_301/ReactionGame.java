package com.example.anju.quickbuzzer_301;


/**
 *  Copyright 2015  Anju Eappen

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 * Purpose: This class is intended as a precursor to calculate the reaction times
 * in the reaction time activity.
 *
 * Design Rationale: This class has some Reaction Timer related responsibilities that don't appear to
 * fit as well with
 *
 * Issues: This class, although made with the intent to follow OO principles, has responsibilitites that
 * may have fit into the DataBin class.
 *
 */
public class ReactionGame {

    private Long sTime;
    private Long currReactionTime;


    public ReactionGame(){
    }

    public void startIteration(){
        sTime = System.currentTimeMillis();
    }

    public void endIteration(){
        currReactionTime =  System.currentTimeMillis() - sTime;
        DataBin.getInstance().addReactionTime(currReactionTime);
        currReactionTime = null;
    }

}
