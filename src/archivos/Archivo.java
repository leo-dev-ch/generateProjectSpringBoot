/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author lgaray
 */
public class Archivo {

    private String dirOrigen;
    private String dirDestino;
    private String model;
    private String packe;

    public Archivo() {
    }

    public String getDirOrigen() {
        return dirOrigen;
    }

    public void setDirOrigen(String dirOrigen) {
        this.dirOrigen = dirOrigen;
    }

    public String getDirDestino() {
        return dirDestino;
    }

    public void setDirDestino(String dirDestino) {
        this.dirDestino = dirDestino;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPacke() {
        return packe;
    }

    public void setPacke(String packe) {
        this.packe = packe;
    }

    public Boolean crearDirectorios() {
        for (int i = 0; i < getDiretorios().size(); i++) {
            String temp = this.dirDestino + '/' + getDiretorios().get(i);
            File directorio = new File(temp);
            if (!directorio.exists()) {
                directorio.mkdir();
            }
        }

        return true;
    }

    public ArrayList getDiretorios() {
        ArrayList<String> dir = new ArrayList();
        dir.add("controller");
        dir.add("repository");
        dir.add("service");
        dir.add("serviceImplement");
        return dir;
    }

    public File[] getArchivos() {
        String sDirectorio = this.getDirOrigen() + "/"+this.getModel();
        File f = new File(sDirectorio);
        File[] ficheros = f.listFiles();

        for (int x = 0; x < ficheros.length; x++) {
            System.out.println(ficheros[x].getName());
        }
        return ficheros;
    }

    public Boolean crearArchivos() throws IOException {
        File fichero;
        String nameFile;
        Theme plantilla;
        //creamos los directorios
        for (int i = 0; i < getDiretorios().size(); i++) {
            String temp = this.dirDestino + '/' + getDiretorios().get(i);
            System.out.println("dir tem "+temp);
            //creamos los ficheros
            for (int x = 0; x < getArchivos().length; x++) {
                nameFile = getArchivos()[x].getName(); //nombre del archivo

                String[] parts = nameFile.split("\\."); //separamos el nombre de la extension
                String temParts = parts[0]; //nombre temporal del archivo
                String dir = (String) getDiretorios().get(i); //nombre del directorio

                dir = Character.toUpperCase(dir.charAt(0)) + dir.substring(1, dir.length()); //colocamos la 1er letra en mayuscula
             //   System.out.println(dir);
                nameFile = temParts + "" + dir + ".java"; //armamos el nombre
                System.out.println("    nomb "+nameFile);
                fichero = new File(temp, nameFile);

                try {
                    // A partir del objeto File creamos el fichero físicamente
                    if (fichero.createNewFile()) {
                        String texto;
                        plantilla = new Theme(temParts,this.getPacke());// le enviamos el nombre del archivo
                        texto = plantilla.setTheme(dir);
                        System.out.println(texto);
                        //si no existe el archivo, lo creamos y escribimos en el
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));

                        bw.write(texto);
                        bw.close();
                    } else {
                        //JOptionPane.showMessageDialog(null, "No ha podido ser creado el fichero");
                        // System.out.println("No ha podido ser creado el fichero");
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    return false;
                }

            }
        }
        return true;
    }
}
