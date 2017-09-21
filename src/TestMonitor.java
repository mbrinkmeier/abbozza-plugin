
import de.uos.inf.did.abbozza.AbbozzaLocale;
import de.uos.inf.did.abbozza.AbbozzaLogger;
import de.uos.inf.did.abbozza.AbbozzaServer;
import de.uos.inf.did.abbozza.monitor.MonitorPanel;
import de.uos.inf.did.abbozza.plugin.Plugin;
import de.uos.inf.did.abbozza.plugin.PluginHandler;
import de.uos.inf.did.abbozza.plugin.PluginListener;
import javax.swing.JPopupMenu;

/*
 * Copyright 2017 michael.
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

/**
 *
 * @author michael
 */
public class TestMonitor extends MonitorPanel implements PluginListener {

    private static TestMonitor monitorPanel = null;
    
    /**
     * Creates new form TestMonitor
     */
    public TestMonitor() {
        AbbozzaLogger.info("TESTPLUGIN PANEL");
        initComponents();
     
        Plugin plugin = AbbozzaServer.getPluginManager().getPlugin("testplugin");
        PluginHandler handler;
        if ( plugin != null ) { 
            handler = plugin.getHttpHandler();
            handler.addPluginListener(this);
        }
        monitorPanel = this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();

        jScrollPane1.setViewportView(textPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables

    @Override
    public void processMessage(String msg) {
        textPane.setText(msg);
    }

    @Override
    public JPopupMenu getPopUp() {
        return null;
    }

    @Override
    public String getName() {
        AbbozzaLogger.info(AbbozzaLocale.entry("gui.test_monitor_title"));
        return AbbozzaLocale.entry("gui.test_monitor_title");
    }
    
    public static TestMonitor getPanel() {
        return monitorPanel;
    }
    
    public void addText(String text) {
        String t = textPane.getText();
        textPane.setText(t + text + "\n");
    }

    @Override
    public void receivedMessage(String msg) {
        AbbozzaLogger.info("testplugin : received '" + msg + "'");
        processMessage(msg);
    }
}
