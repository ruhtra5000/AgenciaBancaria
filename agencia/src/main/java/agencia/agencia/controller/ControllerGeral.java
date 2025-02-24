package agencia.agencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import agencia.agencia.service.ServiceGeral;
import org.springframework.web.bind.annotation.RequestParam;


/*
 * Controlador geral da aplicação
 */

@Controller
@RequestMapping("/")
public class ControllerGeral {

    @Autowired
    private ServiceGeral serviceGeral;
    
    @GetMapping
    public String home() {
        /*ModelAndView mv = new ModelAndView("home");*/
        for (var a : serviceGeral.realizarConsulta1()) {
            System.out.println(a.getNomecliente());
        }
        return "home";
    }
    /*
    @GetMapping("consulta1")
    public ModelAndView consulta1() {
        ModelAndView mv = new ModelAndView("");
        return mv;
    }

    @GetMapping("consulta2")
    public ModelAndView consulta2() {
        ModelAndView mv = new ModelAndView("");
        return mv;
    }

    @GetMapping("consulta3")
    public ModelAndView consulta3() {
        ModelAndView mv = new ModelAndView("");
        return mv;
    }

    @GetMapping("consulta4")
    public ModelAndView consulta4() {
        ModelAndView mv = new ModelAndView("");
        return mv;
    }
    */
    
}
