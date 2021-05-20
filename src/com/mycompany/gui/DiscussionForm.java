/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Style;
import com.mycompany.entities.Discussion;
import com.mycompany.entities.Message;
import com.mycompany.myapp.ServiceDiscussion;
import com.mycompany.myapp.ServiceMessage;
import com.mycompany.utilis.Statics;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;

/**
 * GUI builder created Form
 *
 * @author 21656
 */
public class DiscussionForm extends com.codename1.ui.Form {

    boolean affiche = false;

    public DiscussionForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public DiscussionForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.TextField gui_Text_Field_Search = new com.codename1.ui.TextField();
    protected com.codename1.ui.Button gui_Button = new com.codename1.ui.Button();
    protected com.codename1.ui.Container gui_Container = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    protected com.codename1.ui.TextField gui_Text_Field_Message = new com.codename1.ui.TextField();
    protected com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    protected com.codename1.ui.Button gui_Button_Message = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Text_Field_Search.addActionListener(callback);
        gui_Button.addActionListener(callback);
        gui_Text_Field_Message.addActionListener(callback);
        gui_Button_Message.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();

            if(sourceComponent.getParent().getLeadParent() != null && (sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.MultiButton || sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.SpanButton)) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == gui_Text_Field_Search) {
                onText_Field_SearchActionEvent(ev);
            }
            if(sourceComponent == gui_Button) {
                onButtonActionEvent(ev);
            }
            if(sourceComponent == gui_Text_Field_Message) {
                onText_Field_MessageActionEvent(ev);
            }
            if(sourceComponent == gui_Button_Message) {
                onButton_MessageActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(false);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("DiscussionForm");
        setName("DiscussionForm");
        gui_Text_Field_Search.setPreferredSizeStr("134.39154mm 9.259259mm");
        gui_Text_Field_Search.setUIID("TextField_Search");
                gui_Text_Field_Search.setInlineStylesTheme(resourceObjectInstance);
        gui_Text_Field_Search.setName("Text_Field_Search");
        gui_Button.setPreferredSizeStr("47.089947mm inherit");
        gui_Button.setText("Search");
                gui_Button.setInlineStylesTheme(resourceObjectInstance);
        gui_Button.setName("Button");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Button,"\ue8b6".charAt(0));
        gui_Container.setPreferredSizeStr("inherit 129.62962mm");
        gui_Container.setUIID("Container_Discussion");
                gui_Container.setInlineStylesTheme(resourceObjectInstance);
        gui_Container.setName("Container");
        gui_Text_Field_Message.setPreferredSizeStr("74.60317mm inherit");
        gui_Text_Field_Message.setUIID("TextField_Message");
                gui_Text_Field_Message.setInlineStylesTheme(resourceObjectInstance);
        gui_Text_Field_Message.setName("Text_Field_Message");
        gui_Container_1.setPreferredSizeStr("93.386246mm 119.31217mm");
        gui_Container_1.setUIID("Container_Message");
                gui_Container_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_1.setName("Container_1");
        gui_Button_Message.setPreferredSizeStr("13.227513mm 8.730159mm");
        gui_Button_Message.setUIID("Button_Message");
                gui_Button_Message.setInlineStylesTheme(resourceObjectInstance);
        gui_Button_Message.setInlineAllStyles("alignment:center;");
        gui_Button_Message.setName("Button_Message");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Button_Message,"\ue163".charAt(0));
        addComponent(gui_Text_Field_Search);
        addComponent(gui_Button);
        addComponent(gui_Container);
        addComponent(gui_Text_Field_Message);
        addComponent(gui_Container_1);
        addComponent(gui_Button_Message);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Text_Field_Search.getParent().getLayout()).setInsets(gui_Text_Field_Search, "1.0582011mm 27.93296% auto 0.0mm").setReferenceComponents(gui_Text_Field_Search, "-1 -1 -1 -1").setReferencePositions(gui_Text_Field_Search, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Button.getParent().getLayout()).setInsets(gui_Button, "2.3809524mm 3.4391534mm auto 1.322751mm").setReferenceComponents(gui_Button, "-1 -1 -1 0 ").setReferencePositions(gui_Button, "0.0 0.0 0.0 1.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container.getParent().getLayout()).setInsets(gui_Container, "0.0mm 49.16201% 0.0mm 1.5873016mm").setReferenceComponents(gui_Container, "1 -1 -1 -1").setReferencePositions(gui_Container, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Text_Field_Message.getParent().getLayout()).setInsets(gui_Text_Field_Message, "auto 17.897728% 0.26455027mm 0.7936508mm").setReferenceComponents(gui_Text_Field_Message, "-1 -1 -1 2 ").setReferencePositions(gui_Text_Field_Message, "0.0 0.0 0.0 1.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container_1.getParent().getLayout()).setInsets(gui_Container_1, "0.0mm 0.0mm 0.0mm 50.698322%").setReferenceComponents(gui_Container_1, "1 -1 3 -1").setReferencePositions(gui_Container_1, "1.0 0.0 1.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Button_Message.getParent().getLayout()).setInsets(gui_Button_Message, "0.0mm 0.0mm 0.26455027mm 2.380951mm").setReferenceComponents(gui_Button_Message, "3 -1 -1 3 ").setReferencePositions(gui_Button_Message, "0.0 0.0 0.0 1.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void onclick_btn() {

    }

    public void onButton_MessageActionEvent(com.codename1.ui.events.ActionEvent evv) {
        if (gui_Text_Field_Message.getText() == "") {
            Dialog.show("Message vide", "Veuillez remplir le champ", "Annuler", null);
        } else {
            Dialog.show("Hi", "Le Message envoyer  :  " + gui_Text_Field_Message.getText(), "OK", null);

            InfiniteProgress ip = new InfiniteProgress();

            final Dialog iDialog = ip.showInfiniteBlocking();

            //appelle Service
            Message m = new Message();
            String contenu = gui_Text_Field_Message.getText();
            m.setContenu(contenu);
            revalidate();
            iDialog.dispose();
            ServiceMessage.getInstance().ajoutMessage(m);
            gui_Container_1.removeAll();
//           Message m = new Message( 0, 0, gui_Text_Field_Message.getText(), null);
//           ServiceMessage.getInstance().ajoutMessage(m);
            gui_Text_Field_Message.setText("");

        }

    }

    public void onButtonActionEvent(com.codename1.ui.events.ActionEvent ev) {
        gui_Container.removeAll();
        if (gui_Text_Field_Search.getText() == "") {
            Dialog.show("Voici", "Tous les discussion ", "OK", null);

            InfiniteProgress ip = new InfiniteProgress();

            final Dialog iDialog = ip.showInfiniteBlocking();

//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //appelle Service
            ArrayList<Discussion> listD = ServiceDiscussion.getInstance().AffichageDiscussions();

            for (Discussion d : listD) {
                Button b = new Button("" + d.getId());
                b.addActionListener(e -> {

                    gui_Container_1.removeAll();
                    Discussion d1 = new Discussion();
                    d1.setId(d.getId());
                    d1.setId_user1(d.getId_user1());
                    d1.setId_user2(d.getId_user2());
                    d1.setDate_discussion(d1.getDate_discussion());
                    Statics.SelectedDiscussion=d1;
                        ArrayList<Message> listM = ServiceMessage.getInstance().AffichageMessage(d);
                        for (Message m : listM) {

                            Label lb = new Label("" + m.getId() + " : " + m.getContenu());

                            Button buttonDelete = new Button("Delete");
                            buttonDelete.addActionListener(evtdelete -> {
                                ServiceMessage.getInstance().deleteMsg(m);
                                revalidate();
                                clearClientProperties();

                            });

                            Button buttonModif = new Button("Modif");
                            buttonModif.addActionListener(evtmodif -> {
                                ServiceMessage.getInstance().modifMsg(m, gui_Text_Field_Message);
                                revalidate();
                                gui_Text_Field_Message.setText("");
                            });
                            gui_Container_1.add(lb);
                            gui_Container_1.add(buttonDelete);
                            gui_Container_1.add(buttonModif);
                            revalidate();
                        }
                    

                    b.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_NONE);
                    b.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);

                });
                gui_Container.add(b);

                revalidate();

            }
            iDialog.dispose();

            gui_Text_Field_Search.setText("");

        } else {
            Dialog.show("Zone de text vide", "Veuillez remplir le champ", "Annuler", null);
            gui_Text_Field_Search.setText("");
        }

    }

    public void onText_Field_SearchActionEvent(com.codename1.ui.events.ActionEvent ev) {
    }

    public void onText_Field_MessageActionEvent(com.codename1.ui.events.ActionEvent ev) {
    }
}
