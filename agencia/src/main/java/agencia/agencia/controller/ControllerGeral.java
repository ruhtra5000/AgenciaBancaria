package agencia.agencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import agencia.agencia.service.ServiceGeral;

/*
 * Controlador geral da aplicação
 */

@Controller
@RequestMapping("/")
public class ControllerGeral {

    @Autowired
    private ServiceGeral serviceGeral;
    
    @GetMapping
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }
    
    @GetMapping("consulta1")
    public ModelAndView consulta1() {
        ModelAndView mv = new ModelAndView("consulta1");
        mv.addObject("clientes", serviceGeral.realizarConsulta1());
        return mv;
    }

    @GetMapping("consulta2")
    public ModelAndView consulta2() {
        ModelAndView mv = new ModelAndView("consulta2");
        mv.addObject("clientes", serviceGeral.realizarConsulta2());
        return mv;
    }

    @GetMapping("consulta3")
    public ModelAndView consulta3_page() {
        ModelAndView mv = new ModelAndView("consulta3_input");
        return mv;
    }

    @GetMapping("consulta3/{nomecliente}")
    public ModelAndView consulta3(@PathVariable("nomecliente") String nomecliente) {
        ModelAndView mv = new ModelAndView("consulta3");
        mv.addObject("transacoes", serviceGeral.realizarConsulta3(nomecliente));
        return mv;
    }

    @GetMapping("consulta4")
    public ModelAndView consulta4() {
        ModelAndView mv = new ModelAndView("consulta4");
        return mv;
    }
}
