package Model;

import Controller.Pelicula;
import Controller.Pelicula3D;
import Controller.PeliculaInfantil;
import Controller.PeliculaNormal;
import Controller.Snack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Catalogo {

    public ArrayList<Pelicula> cargarPeliculasCSV(String ruta) {
        ArrayList<Pelicula> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length < 6) continue;

                String tipo = datos[0].trim();
                String titulo = datos[1].trim();
                String sinopsis = datos[2].trim();
                String genero = datos[3].trim();
                String clasificacion = datos[4].trim();
                int duracion = Integer.parseInt(datos[5].trim());

                Pelicula peli = null;
                if (tipo.equalsIgnoreCase("Normal")) {
                    peli = new PeliculaNormal(titulo, sinopsis, genero, clasificacion, duracion);
                } else if (tipo.equalsIgnoreCase("3D")) {
                    peli = new Pelicula3D(titulo, sinopsis, genero, clasificacion, duracion);
                } else if (tipo.equalsIgnoreCase("Infantil")) {
                    peli = new PeliculaInfantil(titulo, sinopsis, genero, clasificacion, duracion);
                }
                if (peli != null) {
                    lista.add(peli);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo de peliculas: " + e.getMessage());
        }
        return lista;
    }

    public ArrayList<Snack> cargarSnacksCSV(String ruta) {
        ArrayList<Snack> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length < 2) continue;

                String nombre = datos[0].trim();
                double precio = Double.parseDouble(datos[1].trim().replace(",", "."));
                lista.add(new Snack(nombre, precio));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo de snacks: " + e.getMessage());
        }
        return lista;
    }
}
