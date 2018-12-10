package co.simplon.springboot.apprenant.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *  Apprenant
 *  pour Lombok : https://projectlombok.org/features/Data
 *  
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude= {"id"})
@Entity
public class Apprenant implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String prenom;
	private String nom;
	private String email;

}
