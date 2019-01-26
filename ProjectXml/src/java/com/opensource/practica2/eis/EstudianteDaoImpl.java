/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensource.practica2.eis;

import com.opensource.practica2.domain.Estudiantes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EstudianteDaoImpl implements EstudianteDao {

    @PersistenceContext(unitName = "ProjectXmlPU")
    EntityManager em;

    @Override
    public List<Estudiantes> findAllEstudiantes() {
        return em.createNamedQuery("Estudiantes.findAll").getResultList();
    }

    @Override
    public Estudiantes findEstudiantesById(Estudiantes estudiante) {
        return em.find(Estudiantes.class, estudiante.getId());
    }

    @Override
    public Estudiantes findEstudiantesByCedula(Estudiantes estudiante) {
        Query query = em.createQuery("FROM Estudiantes e WHERE e.cedulaEstudiante = :cedulaEstudiante");
        query.setParameter("cedulaEstudiante", estudiante.getCedulaEstudiante());
        return (Estudiantes) query.getSingleResult();
    }

    @Override
    public void insertEstudiantes(Estudiantes estudiante) {
        em.persist(estudiante);
    }

    @Override
    public void updateEstudiantes(Estudiantes estudiante) {
        em.merge(estudiante);
    }

    @Override
    public void deleteEstudiantes(Estudiantes estudiante) {
        estudiante = em.getReference(Estudiantes.class, estudiante.getId());
        em.remove(estudiante);
    }
}
