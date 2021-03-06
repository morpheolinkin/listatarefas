package com.jefferson.listatarefas.controllers;

import com.jefferson.listatarefas.models.Evento;
import com.jefferson.listatarefas.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository er;

    @GetMapping(value = "/cadastrarEvento")
    public String form() {
        return "evento/formEvento";
    }

    @PostMapping(value = "/cadastrarEvento")
    public String form(Evento evento) {
        er.save(evento);

        return "redirect:/cadastrarEvento";
    }

    @RequestMapping("/eventos")
    public ModelAndView listaEventos() {
        ModelAndView mv = new ModelAndView("listaEventos");
        Iterable<Evento> eventoIterable = er.findAll();
        mv.addObject("eventos", eventoIterable);
        return mv;
    }

    @RequestMapping("/deletarEvento")
    public String deletarEvento(long codigo) {
        Evento evento = er.findByCodigo(codigo);
        er.delete(evento);
        return "redirect:/eventos";
    }
}
