package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;
import java.lang.String;
import java.lang.Object;



@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private mascotasRepository mascotasRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @PostMapping(path="/mascotas/add") // Map ONLY POST Requests http://localhost:8080/mascotas/add
  public @ResponseBody String addNewMascotas (@RequestParam String name, @RequestParam String email) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    User n = new User();
    n.setNombre(nombre);
    n.setRaza(raza);
    n.setPropietario(propietario);
    mascotasRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/mascotas/all")     // http://localhost:8080/demo/all
  public @ResponseBody Iterable<User> getAllMacotas() {
    // This returns a JSON or XML with the users
    return mascotasRepository.findAll();
  }

  @PutMapping(path="/mascotas/edit")
  public @ResponseBody String editMascotas(@RequestParam Integer id, @RequestParam String nombre, @RequestParam String raza, @RequestParam String propietario) {
    User n = new User();
    n.setNombre(nombre);
    n.setRaza(raza);
    n.setPropietario(propietario);
    return "Updated";
  }

  @GetMapping(path="/mascotas/ver/{id}")
  public @ResponseBody Mascotas getOneMascotas(@PathVariable Integer id) {
    return mascotasRepository.findById(id).orElse(null);
  }

  @DeleteMapping(path="/mascotas/del")
  public @ResponseBody String deleteMascotas(@RequestParam Integer id) {
    Mascotas n = new Mascotas();
    n.setId(id);
    mascotasRepository.delete(n);
    return "Deleted";
  }

  @GetMapping(path="/mascotas/get/report")
  public @ResponseBody List reporte() {
    String sql = "SELECT CONCAT(nombre, ' ==> ', raza, ' ==> ' , propietario) as mycol FROM user";
    List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
    return results;
  }





}