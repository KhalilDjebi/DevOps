package tn.esprit.rh.achat;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.This;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SpringBootTest(classes = CategorieProduit.class)
@RunWith(MockitoJUnitRunner.class)
public class CategorieProductTest {
    @Mock
	CategorieProduitRepository categorieProduitRepository;

	@InjectMocks
	CategorieProduitServiceImpl CategorieProduitServiceImpl;

	CategorieProduit categorieProduit = new CategorieProduit(Long.valueOf(1), "123", "f1", null);

	List<CategorieProduit> listCategorieProduit = new ArrayList<CategorieProduit>() {
		{
			add(new CategorieProduit(Long.valueOf(2), "456", "f2", null));
			add(new CategorieProduit(Long.valueOf(3), "789", "f3", null));
		}
	};
	
	@Test
	public void TestretrieveAllCategorie() {

		Mockito.when(categorieProduitRepository.findAll()).thenReturn(listCategorieProduit);
		List<CategorieProduit> list = CategorieProduitServiceImpl.retrieveAllCategorieProduits();
		assertEquals(2, list.size());
		System.out.println("Retrieve all");
	}
	
	@Test
	public void testAddCategorie() {

		Mockito.when(categorieProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);
		CategorieProduit categorieProduit1 = CategorieProduitServiceImpl.addCategorieProduit(categorieProduit);
		Assertions.assertNotNull(categorieProduit1);
		System.out.println("added !");
	}
	
	@Test
	public void testdeleteCategorie() {
		CategorieProduit categorieProduit1 = new CategorieProduit(Long.valueOf(4), "741", "f4", null);
		CategorieProduitServiceImpl.deleteCategorieProduit(categorieProduit1.getIdCategorieProduit());
		Mockito.verify(categorieProduitRepository).deleteById(categorieProduit1.getIdCategorieProduit());
		System.out.println("deleted");
	}
	
	@Test
	public void testUpdateCategorie() {
		categorieProduit.setLibelleCategorie("libelleUpdated");
		Mockito.when(categorieProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);
		CategorieProduit categorieProduit1 = CategorieProduitServiceImpl.updateCategorieProduit(categorieProduit);
		Assertions.assertEquals(categorieProduit.getLibelleCategorie(), categorieProduit1.getLibelleCategorie());
		System.out.println(categorieProduit1);
	}
	
	@Test
	public void testRetrieveCategorie() {

		Mockito.when(categorieProduitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(categorieProduit));
		CategorieProduit categorieProduit1 = CategorieProduitServiceImpl.retrieveCategorieProduit(Long.valueOf(1));
		Assertions.assertNotNull(categorieProduit1);
		System.out.println("Retrieved !");
	}
}
