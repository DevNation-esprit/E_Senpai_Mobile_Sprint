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
import com.mycompany.myapp.entities.Post;
import java.util.ArrayList;

/**
 *
 * @author Methnani
 */
public class ShowPost extends Form {
    
// private    int selectedRow = -1;
//  private int idToDel=0;
//  Dialog d = new Dialog();
//  private String notifText ="";
//  private Button notifBtn = new Button(notifText);
//  private ArrayList<String> ids = new ArrayList<>();
//  
//    public ShowPost (Form previous) {
//        ArrayList<Post> listt = Services.getInstance().affichePost();
//        ArrayList<ArrayList<String>> rrr = new ArrayList<>();
//           Form current;
//           current=this;
//        
//        Button btnDel = new Button();
//        btnDel.setText("Delete Selected Post");
//
//        for(Post p : listt){
//        
//            ArrayList<String> rr = new ArrayList<>();
//            rr.add(String.valueOf(p.getId_post()));
//            rr.add(String.valueOf(p.getCaption()));
//            
//            
//            
//            rrr.add(rr);
//        
//        }
//    
//
//        // try over
//        String[][] all = new String[listt.size()][10];
//          int i=0;
//                  for (Post p : listt) {
//                    
//                all[i][0] = String.valueOf(p.getId_post());
//                all[i][1] = p.getCaption();
//                
//                
//                
//                i++;
//     
//            }
//        
//
//        setTitle("Liste des Posts");
//        String[] s = new String[]{"ID ", "Caption"};
//        boolean b = true;
//        
//
//        TableModel model = new DefaultTableModel(s, all, b);
//        Table table = new Table(model) {
//        
//    @Override
//    protected Component createCell(Object value, int row, int column, boolean editable) { 
//        Component cell;
//      cell = super.createCell(value, row, column, editable);
//
//        if (row < 0) {
//            cell.getAllStyles().setBgColor(0255255);
//            cell.getAllStyles().setBgTransparency(255);
//        } else {
//           
//            cell = new Button(value.toString());
//            cell.setUIID("TableCell");
//              Dialog d = new Dialog();
//            ((Button) cell).addActionListener(e -> {
//                selectedRow = row;
//                setModel(getModel());
//                
//                // create dialog here
//         
//                
//                if(d.show("Title","Ech bech taamil ", "Ok","Cancel") ) {
//                
//                //delete
//             
//                    System.out.println("deleted");
//                 
//                               int id=Integer.parseInt(all[row][0]);
//                    Services.getInstance().SuppBlog(id);
//                 
//                    new ShowBlog(current).show();
// 
//                } else{
//                
//                // close
//                    System.out.println("closed");
//            
//                    System.out.println("End and clodse"+s);
//                  
//                    
//                }
//           
//
//            });
//        }
//
//        if (row > -1 && row % 2 == 0) {
//            // pinstripe effect
//            cell.getAllStyles().setBgColor(0xeeeeee);
//            cell.getAllStyles().setBgTransparency(255);
//        }
//
//
//     if(selectedRow > -1 && selectedRow == row) {
//            cell.getAllStyles().setBgColor(0xff0000);
//            cell.getAllStyles().setBgTransparency(100);
//        }
//       
//        return cell;
//    }
//
//        };
//        
//        add(table);
//        btnDel.getAllStyles().setBgColor(0255255);
//      
//      
//        add(btnDel);
//        add(notifBtn);
//        
//            if(selectedRow !=-1){
//       
//             System.out.println("selectesd  row"+selectedRow);
//       }
//    
//}
}
