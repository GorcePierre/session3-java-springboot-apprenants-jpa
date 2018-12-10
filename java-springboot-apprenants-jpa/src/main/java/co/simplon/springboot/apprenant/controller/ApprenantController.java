package co.simplon.springboot.apprenant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.springboot.apprenant.model.Apprenant;

/**
 * 
 * Controller
 *
 */
@RestController
@RequestMapping("/api")
public class ApprenantController {

	@Autowired
	private co.simplon.springboot.apprenant.repository.ApprenantRepository apprenantRepository;
	
	
	
	public ApprenantController() {}

	/**
	 * Retourner tous les apprenants
	 * @return
	 */
	@RequestMapping(value = "/apprenants", method = RequestMethod.GET)
	public ResponseEntity<?> getAllApprenants(){
		List<Apprenant> listeApprenants = null;
		try {
			listeApprenants = apprenantRepository.findAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listeApprenants);
	}
	
	/**
	 * rechercher
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/apprenant/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getApprenant(@PathVariable Integer id){
		Apprenant apprenant = null;
				
		try {
			apprenant =apprenantRepository.findOne(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if(apprenant == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
		return ResponseEntity.status(HttpStatus.OK).body(apprenant);
	}
	
	/**
	 * ajouter
	 * @param apprenant
	 * @return
	 */
	@RequestMapping(value = "/apprenant", method = RequestMethod.POST)
	public ResponseEntity<?> addApprenant(@RequestBody Apprenant apprenant){
		Apprenant resultApprenant = null;
		String prenom = apprenant.getPrenom();
		if((prenom == null) || (prenom.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Il manque le prénom !");
		
		String nom = apprenant.getNom();
		if((nom == null) || (nom.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Il manque le nom !");
		
		try {
			resultApprenant = apprenantRepository.saveAndFlush(apprenant);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resultApprenant);
	}
	
	/**
	 * Mettre à jour
	 * @param apprenant
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/apprenant/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateApprenant(@RequestBody Apprenant apprenant,@PathVariable Integer id) throws Exception {
		Apprenant resultApprenant = null;
		String prenom = apprenant.getPrenom();
		if((prenom == null) || (prenom.isEmpty()))
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Il manque le prénom !");
		
		String nom = apprenant.getNom();
		if((nom == null) || (nom.isEmpty()))
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Il manque le nom !");
		
		try {
			resultApprenant = apprenantRepository.save(apprenant);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resultApprenant);
	}
	
	/**
	 * Détruire
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/apprenant/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteApprenant(@PathVariable Integer id){
		try {
		apprenantRepository.delete(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
}
