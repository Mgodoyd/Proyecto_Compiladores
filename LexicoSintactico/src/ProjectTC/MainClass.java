/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectTC;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author jess23
 */
public class MainClass {
   
    
    public static void main(String[] args) throws Exception{
        UIManager.put("OptionPane.background", new ColorUIResource(187,187,187));
        UIManager.put("OptionPane.messagebackground", new ColorUIResource(187,187,187));
        UIManager.put("Panel.background", new ColorUIResource(187,187,187));
        
      //  Load VerCarga = new Load(null,false);
        
     
        String ruta1 = "src/ProjectTC/Analizador/AnalizadorLexico.flex"; 
        String ruta2 = "src/ProjectTC/Analizador/AnalizadorLex_Cup.flex";
        String[] rutaS = {"-parser", "Sintax", "src/ProjectTC/Analizador/Sintax.cup"};
        generar(ruta1, ruta2, rutaS);
        
     
      Load_ pre= new Load_();
        pre.setVisible(true);
        MainMenu iniciar= new MainMenu();
        try{
            for(int i=0; i<=100; i++){
                Thread.sleep(60);
                pre.porcentaje.setText(Integer.toString(i)+"%");
                pre.barra.setValue(i);
                
                if(i==100){
                    pre.setVisible(false);
                    iniciar.setVisible(true);
                }
            }
        }catch (Exception e){
        }
    }
    
    
    public static void generar(String ruta1, String ruta2, String[] rutaS) throws IOException, Exception{
        File archivo;
        archivo = new File(ruta1);
        JFlex.Main.generate(archivo);
        archivo = new File(ruta2);
        JFlex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        
        Path rutaSym = Paths.get("src/ProjectTC/Analizador/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get("sym.java"), 
                Paths.get("src/ProjectTC/Analizador/sym.java")
        );
        Path rutaSin = Paths.get("src/ProjectTC/Analizador/Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get("Sintax.java"), 
                Paths.get("src/ProjectTC/Analizador/Sintax.java")
        );
    }
}
