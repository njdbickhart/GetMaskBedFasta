/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getmaskbedfasta;

import GetCmdOpt.SimpleCmdLineParser;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author Derek.Bickhart
 */
public class GuiCmdLineParser extends SimpleCmdLineParser{
    private boolean isGui = false;

    GuiCmdLineParser(String usage) {
        super.usage = usage;
    }
    
    /**
     * This wraps the parity checking and argument parsing of the GetCmdOpt abstract class.
     * It does not allow mode designation for programs, but it is a very fast and simple 
     * implementation designed to create fast command line options for a program.
     * It contains a help parser (-h, -H, or --help) and usage printer.
     * @param args The program's command line options
     * @param flags The flag string (see javadocs for GetCmdOpt for this two character scheme)
     * @param required A concatenated string containing the required flags for the program
     */
    @Override
    public void GetAndCheckOpts(String[] args, String flags, String required){
        try{
            if(args.length == 0){
                GuiCheck();
                return;
            }
                
            for(String a : args){
                if(a.equals("-h")
                        || a.equals("-H")
                        || a.equals("--help")){
                    System.out.println(this.usage);
                    System.exit(0);
                }
            }
            
            this.ProcessCmdString(args, flags);
            if(!this.SimpleParityCheck(required)){
                GuiCheck();
            }
        }catch(Exception ex){
            
            System.out.println("Error with programmer input!" + ex.getMessage());
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    private void GuiCheck() {
        if(GraphicsEnvironment.isHeadless()){
            System.out.println("Missing key command line arguments!");
            System.out.println(this.usage);
            System.exit(0);
        }else{
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(GetMaskFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(GetMaskFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(GetMaskFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(GetMaskFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new GetMaskFrame().setVisible(true);
                }
            });
            
            this.isGui = true;
        }
    }
    
    public boolean isGui(){
        return this.isGui;
    }
}
