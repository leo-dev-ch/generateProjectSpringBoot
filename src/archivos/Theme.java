/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

/**
 *
 * @author lgaray
 */
public class Theme {

    private String name;
    private String pack;

    public Theme() {
    }

    public Theme(String name,String pack) {
        this.name = Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length()); //colocamos la 1er letra en mayuscula
        this.pack = pack;
    }

    public String getRepository() {

        String temp = "package "+this.pack+".repository;\n"
                + "\n"
                + "import org.springframework.data.jpa.repository.JpaRepository;\n"
                + "\n"
                + "import "+this.pack+".model.Ejemplo;\n"
                + "\n"
                + "public interface EjemploRepository extends JpaRepository<Ejemplo, Long>{\n"
                + "\n"
                + "}";
        temp = temp.replace("Ejemplo", this.name);
        return temp;
    }

    public String getService() {
        String temp = "package "+this.pack+".service;\n"
                + "\n"
                + "import "+this.pack+".model.Ejemplo;\n"
                + "\n"
                + "\n"
                + "/**\n"
                + " *\n"
                + " * @author lgaray\n"
                + " */\n"
                + "public interface EjemploService{\n"
                + "\n"
                + "   Iterable<Ejemplo> listAllEjemplo();\n"
                + "\n"
                + "    Ejemplo getEjemploById(Long id);\n"
                + "\n"
                + "    Ejemplo saveEjemplo(Ejemplo item);\n"
                + "\n"
                + "    void deleteEjemplo(Long id);\n"
                + "\n"
                + "}\n";
        temp = temp.replace("Ejemplo", this.name);
        return temp;
    }

    public String getServiceImplement() {
        String temp = "package "+this.pack+".serviceImplement;\n"
                + "\n"
                + "import "+this.pack+".model.Ejemplo;\n"
                + "import "+this.pack+".repository.EjemploRepository;\n"
                + "import "+this.pack+".service.EjemploService;\n"
                + "import org.springframework.beans.factory.annotation.Autowired;\n"
                + "import org.springframework.stereotype.Service;\n"
                + "\n"
                + "@Service\n"
                + "public class EjemploServiceImplement implements EjemploService {\n"
                + "\n"
                + "    private EjemploRepository ejemploRepository;\n"
                + "\n"
                + "    @Autowired\n"
                + "    public void setEjemploRepository(EjemploRepository itemRepository) {\n"
                + "        this.ejemploRepository = itemRepository;\n"
                + "    }\n"
                + "\n"
                + "    @Override\n"
                + "    public Iterable<Ejemplo> listAllEjemplo() {\n"
                + "        return ejemploRepository.findAll();\n"
                + "    }\n"
                + "\n"
                + "    @Override\n"
                + "    public Ejemplo getEjemploById(Long id) {\n"
                + "        return ejemploRepository.findOne(id);\n"
                + "    }\n"
                + "\n"
                + "    @Override\n"
                + "    public Ejemplo saveEjemplo(Ejemplo item) {\n"
                + "        return ejemploRepository.save(item);\n"
                + "    }\n"
                + "\n"
                + "    @Override\n"
                + "    public void deleteEjemplo(Long id) {\n"
                + "        ejemploRepository.delete(id);\n"
                + "    }\n"
                + "}";

        temp = temp.replace("Ejemplo", this.name);
        temp = temp.replace("ejemplo", this.name);
        return temp;
    }

    public String getController() {
        String temp = "package "+this.pack+".controller;\n"
                + "\n"
                + "import "+this.pack+".model.Ejemplo;\n"
                + "import "+this.pack+".service.EjemploService;\n"
                + "import "+this.pack+".serviceImplement.EjemploServiceImplement;\n"
                + "import org.springframework.stereotype.Controller;\n"
                + "import org.springframework.ui.Model;\n"
                + "import org.springframework.web.bind.annotation.RequestMapping;\n"
                + "\n"
                + "import org.springframework.beans.factory.annotation.Autowired;\n"
                + "import org.springframework.web.bind.annotation.PathVariable;\n"
                + "import org.springframework.web.bind.annotation.RequestMethod;\n"
                + "\n"
                + "@Controller\n"
                + "\n"
                + "public class EjemploController {\n"
                + "\n"
                + "    private EjemploServiceImplement EjemploService;\n"
                + "\n"
                + "    @Autowired\n"
                + "    public void setEjemploService(EjemploService EjemploService) {\n"
                + "        this.EjemploService = (EjemploServiceImplement) EjemploService;\n"
                + "    }\n"
                + "\n"
                + "    /**\n"
                + "     *\n"
                + "     * @param model\n"
                + "     * @return\n"
                + "     */\n"
                + "    @RequestMapping(\"/Ejemplos\")\n"
                + "    public String listarEjemplos(Model model) {\n"
                + "        model.addAttribute(\"Ejemplos\", EjemploService.listAllEjemplo());\n"
                + "        return \"Ejemplos/EjemplosListado\";\n"
                + "    }\n"
                + "\n"
                + "    /**\n"
                + "     * Save Ejemplo to database.\n"
                + "     *\n"
                + "     * @param Ejemplo\n"
                + "     * @return\n"
                + "     */\n"
                + "    @RequestMapping(value = \"EjemploSave\", method = RequestMethod.POST)\n"
                + "    public String saveEjemplo(Ejemplo Ejemplo) {\n"
                + "        EjemploService.saveEjemplo(Ejemplo);\n"
                + "        return \"redirect:/Ejemplos/\" + Ejemplo.getIdEjemplo();\n"
                + "    }\n"
                + "\n"
                + "    /**\n"
                + "     * View a specific Ejemplo by its id.\n"
                + "     *\n"
                + "     * @param id\n"
                + "     * @param model\n"
                + "     * @return\n"
                + "     */\n"
                + "    @RequestMapping(\"Ejemplos/{id}\")\n"
                + "    public String showEjemplo(@PathVariable Long id, Model model) {\n"
                + "        model.addAttribute(\"Ejemplo\", EjemploService.getEjemploById(id));\n"
                + "        return \"Ejemplos/EjemploShow\";\n"
                + "    }\n"
                + "\n"
                + "    /**\n"
                + "     * New Ejemplo.\n"
                + "     *\n"
                + "     * @param model\n"
                + "     * @return\n"
                + "     */\n"
                + "    @RequestMapping(\"Ejemplos/new\")\n"
                + "    public String nuevoEjemplo(Model model) {\n"
                + "        model.addAttribute(\"Ejemplo\", new Ejemplo());\n"
                + "        return \"Ejemplos/EjemploForm\";\n"
                + "    }\n"
                + "\n"
                + "    // Afficher le formulaire de modification du Ejemplo\n"
                + "    @RequestMapping(\"Ejemplos/edit/{id}\")\n"
                + "    public String edit(@PathVariable Long id, Model model) {\n"
                + "        model.addAttribute(\"Ejemplo\", EjemploService.getEjemploById(id));\n"
                + "        return \"Ejemplos/EjemploForm\";\n"
                + "    }\n"
                + "\n"
                + "    /**\n"
                + "     * Delete Ejemplo by its id.\n"
                + "     *\n"
                + "     * @param id\n"
                + "     * @return\n"
                + "     */\n"
                + "    @RequestMapping(\"Ejemplos/delete/{id}\")\n"
                + "    public String delete(@PathVariable Long id) {\n"
                + "        EjemploService.deleteEjemplo(id);\n"
                + "        return \"redirect:/Ejemplos\";\n"
                + "    }\n"
                + "\n"
                + "}";
        temp = temp.replace("Ejemplo", this.name);
        return temp;
    }

    public String setTheme(String tipo) {
        String op;
        switch (tipo) {
            case "Repository":
                op = getRepository();
                break;
            case "Service":
                op = getService();
                break;
            case "ServiceImplement":
                op = getServiceImplement();
                break;
            case "Controller":
                op = getController();
                break;
            default:
                op = "";
        }

        return op;
    }
}
