/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.mycompany.myapp.Services.Services;
import com.mycompany.myapp.entities.Blog;
import java.util.ArrayList;

/**
 *
 * @author Methnani
 */
public class ShowBlog extends Form {
    
  private    int selectedRow = -1;
  private int idToDel=0;
  Dialog d = new Dialog();
  private String notifText ="";
  private Button notifBtn = new Button(notifText);
  private ArrayList<String> ids = new ArrayList<>();
  
    public ShowBlog (Form previous) {
        ArrayList<Blog> listt = Services.getInstance().afficheBlog();
        ArrayList<ArrayList<String>> rrr = new ArrayList<>();
           Form current;
           current=this;
        
        Button btnDel = new Button();
        btnDel.setText("Delete Selected Blog");

        for(Blog b : listt){
        
            ArrayList<String> rr = new ArrayList<>();
            rr.add(String.valueOf(b.getId_blog()));
            rr.add(String.valueOf(b.getTitre()));
            rr.add(String.valueOf(b.getContenu()));
            
            
            rrr.add(rr);
        
        }
    

        // try over
        String[][] all = new String[listt.size()][10];
          int i=0;
                  for (Blog b : listt) {
                    
                all[i][0] = String.valueOf(b.getId_blog());
                all[i][1] = b.getTitre();
                all[i][2] = b.getContenu();
                
                
                i++;
     
            }
        

        setTitle("Liste des Blogs");
        String[] s = new String[]{"ID ", "Titre", "Contenu"};
        boolean b = true;
        

        TableModel model = new DefaultTableModel(s, all, b);
        Table table = new Table(model) {
        
    @Override
    protected Component createCell(Object value, int row, int column, boolean editable) { 
        Component cell;
      cell = super.createCell(value, row, column, editable);

        if (row < 0) {
            cell.getAllStyles().setBgColor(0255255);
            cell.getAllStyles().setBgTransparency(255);
        } else {
           
            cell = new Button(value.toString());
            cell.setUIID("TableCell");
              Dialog d = new Dialog();
            ((Button) cell).addActionListener(e -> {
                selectedRow = row;
                setModel(getModel());
                
                // create dialog here
         
                
                if(d.show("Title","Ech bech taamil ", "Ok","Cancel") ) {
                
                //delete
             
                    System.out.println("deleted");
                 
                               int id=Integer.parseInt(all[row][0]);
                    Services.getInstance().SuppBlog(id);
                 
                    new ShowBlog(current).show();
 
                } else{
                
                // close
                    System.out.println("closed");
            
                    System.out.println("End and clodse"+s);
                  
                    
                }
           

            });
        }

        if (row > -1 && row % 2 == 0) {
            // pinstripe effect
            cell.getAllStyles().setBgColor(0xeeeeee);
            cell.getAllStyles().setBgTransparency(255);
        }


     if(selectedRow > -1 && selectedRow == row) {
            cell.getAllStyles().setBgColor(0xff0000);
            cell.getAllStyles().setBgTransparency(100);
        }
       
        return cell;
    }

        };
        
        add(table);
        btnDel.getAllStyles().setBgColor(0255255);
      
      
        add(btnDel);
        add(notifBtn);
        
            if(selectedRow !=-1){
       
             System.out.println("selectesd  row"+selectedRow);
       }
    
}
}
