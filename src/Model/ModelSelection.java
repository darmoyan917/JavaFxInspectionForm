package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;


public final class ModelSelection implements ModelInterface {
    
    private Path ModelFilePath = null;
    private File ModelFile = null;
    
    public ModelSelection(String SelectedMake)
    {
        ModelFilePath = Paths.get("models\\" + SelectedMake.toLowerCase()+".txt");
        ModelFile = ModelFilePath.toFile();
       
    }
    
    public String[] SetModelCombo(){
        int NumOfLines = GetLineNumber();
        String[] ModelCombo = GetModelCombo(NumOfLines);
        return ModelCombo;
    }
    
    public int GetLineNumber(){
        int NumOfLines=0;
        if (Files.exists(ModelFilePath)){
            try(LineNumberReader  lnr = new LineNumberReader(new FileReader(ModelFile))){
    
                lnr.skip(Long.MAX_VALUE);
                NumOfLines = (lnr.getLineNumber()+1);
                lnr.close();
            }
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(new javax.swing.JFrame(), e);
                
            }
    }
        else
            JOptionPane.showMessageDialog(new javax.swing.JWindow(), "The File does not Exist. Line # function.");
         return NumOfLines;
    }
    public  String[] GetModelCombo(int NumOfLines) {
        
        String ModelCombo[] = new String[NumOfLines];        
        
        if (Files.exists(ModelFilePath))  // prevent the FileNotFoundException
        {
            try (BufferedReader in = 
                     new BufferedReader(
                     new FileReader(ModelFile)))
            {
                int i = 0;
                String line = in.readLine();
                while(line != null)
                {
                     
                    ModelCombo[i] = line; 
                    line = in.readLine(); 
                    i++;
                }
            }
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(new javax.swing.JFrame(), e);
                return null;
            }
        }
        else
            JOptionPane.showMessageDialog(new javax.swing.JWindow(), "The File does not Exist.");
        return ModelCombo;            
    }
    
        }
        
    

