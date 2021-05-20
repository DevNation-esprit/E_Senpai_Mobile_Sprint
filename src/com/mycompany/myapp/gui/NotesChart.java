/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.views.DoughnutChart;
import com.codename1.ui.Form;
import java.util.ArrayList;
import java.util.List;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Label;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.services.ServiceTest;





/**
 * Budget demo pie chart.
 */
public class NotesChart extends AbstractDemoChart {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Budget chart for several years";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "The budget per project for several years (doughnut chart)";
  }

  /**
   * Executes the chart demo.
   * 
     * @param idTest
   * @return the built intent
   */
  public Form execute(int idTest) {
    ArrayList<Note> notes = ServiceTest.getInstance().getListNotes(idTest );
    List<double[]> values = new ArrayList<>();
    List<String[]> titles = new ArrayList<>();
    double[] tabvalues = null ;
    String[] tabTitle = null ;
    int[] tabColors = null ;
    if(!notes.isEmpty()){
       tabvalues = new double[notes.size()] ;
       tabTitle = new String[notes.size()] ;
       tabColors = new int[notes.size()];
        
        for(int i = 0;i < notes.size() ; i++){
            tabvalues[i] = notes.get(i).getNoteObtenue() ;
            tabTitle[i] = notes.get(i).getNomEtudiant()+" : "+notes.get(i).getNoteObtenue();
            tabColors[i] = (i+1)*150 ;
        }
    }
    
    values.add(tabvalues); 
    titles.add(tabTitle);

    DefaultRenderer renderer = buildCategoryRenderer(tabColors);
    renderer.setApplyBackgroundColor(true);
    renderer.setBackgroundColor(ColorUtil.rgb(222, 222, 200));
    renderer.setLabelsColor(ColorUtil.GRAY);
    
    DoughnutChart chart = new DoughnutChart(buildMultipleCategoryDataset("Notes", titles, values), renderer);
    ChartComponent c = new ChartComponent(chart);
    return wrap("Chart Notes", c);
    
  }

    @Override
    public Form execute() {
      return null;      
    }

}
