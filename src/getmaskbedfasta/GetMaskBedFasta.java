/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getmaskbedfasta;

import GetCmdOpt.SimpleCmdLineParser;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Derek.Bickhart
 */
public class GetMaskBedFasta {
    public static final String version = "0.0.2";
    private static final String usage = "GetmaskBedFasta version: " + version + System.lineSeparator() +
            "Usage: java -jar GetMaskBedFasta -f <fasta file> -o <output bed file> -s <output stats file>" + System.lineSeparator();
    private static final Logger log = Logger.getLogger(GetMaskBedFasta.class.getName());
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GuiCmdLineParser cmd = new GuiCmdLineParser(usage);
        
        cmd.GetAndCheckOpts(args, "f:o:s:", "fos", "fos", "fasta", "output", "stats");
        
        if(!cmd.isGui()){
            BufferedWriter bed = null, stats = null;

            try {
                bed = Files.newBufferedWriter(Paths.get(cmd.GetValue("output")), Charset.defaultCharset());
                stats = Files.newBufferedWriter(Paths.get(cmd.GetValue("stats")), Charset.defaultCharset());
                stats.write("chr\tchrlen\tsumNs\tlongestN\tavgNLen\tmedianNLen\tstdevNLen\tpercentChrLenN");
                stats.write(System.lineSeparator());
            } catch (IOException ex) {
                log.log(Level.SEVERE, "Could not initialize output files!", ex);
            }

            // Main routine
            try{
                BufferedFastaReader reader = new BufferedFastaReader(cmd.GetValue("fasta"));
                StatContainer statCon;
                while((statCon = reader.readToNextChr(bed)) != null){
                    System.out.println("Working on chr: " + statCon.chrname);
                    stats.write(statCon.getFormatStatStr());
                }
                reader.close();
            }catch(IOException ex){
                log.log(Level.SEVERE, "Error reading fasta file!", ex);
            }

            try{
                bed.close();
                stats.close();
            }catch(IOException ex){
                log.log(Level.SEVERE, "Error closing stat and bed files!", ex);
            }
        }
    }
    
}
