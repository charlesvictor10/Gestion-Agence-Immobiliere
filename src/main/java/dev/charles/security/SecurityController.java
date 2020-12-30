package dev.charles.security;

import java.io.File;
import java.io.FileInputStream;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dev.charles.dao.UtilisateurRepository;
import dev.charles.entities.Utilisateur;

@Controller
public class SecurityController {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Value("${dir.photoUtilisateur}")
	private String photoUtilisateurDir;
	
	@RequestMapping(value="/getPhotoUtilisateur", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhotoUtilisateur(String username) throws Exception {
		File f = new File(photoUtilisateurDir+username);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/connexion")
	public String login(Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
		if(error != null)
			model.addAttribute("errorMessage", "Nom d'utilisateur ou mot de passe incorrect");
		if(logout != null)
			model.addAttribute("message", "Vous vous êtes bien déconnecté");
		return "connexion";
	}
	
	@RequestMapping(value="/inscription", method=RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "inscription";
	}
	
	@RequestMapping(value="/saveUtilisateur", method=RequestMethod.POST)
	public String saveUtilisateur(@Valid Utilisateur u, BindingResult bindingResult, @RequestParam(name="picture") MultipartFile file) throws Exception {
		if(bindingResult.hasErrors()) {
			return "inscription";
		}
		
		if(!(file.isEmpty())) {
			u.setPhotoUtilisateur(file.getOriginalFilename());
		}
		
        u.setPrenomUtilisateur(u.getPrenomUtilisateur());
        u.setNomUtilisateur(u.getNomUtilisateur());
        u.setUsername(u.getUsername());
        u.setEmailUtilisateur(u.getEmailUtilisateur());
        u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        u.setDateNaissanceUtilisateur(u.getDateNaissanceUtilisateur());
        u.setTelephoneUtilisateur(u.getTelephoneUtilisateur());
        u.setProfessionUtilisateur(u.getProfessionUtilisateur());
        u.setNationaliteUtilisateur(u.getNationaliteUtilisateur());
        u.setActive(true);
		utilisateurRepository.save(u);	
		
		if(!(file.isEmpty())) {
			u.setPhotoUtilisateur(file.getOriginalFilename());
			file.transferTo(new File(photoUtilisateurDir+u.getUsername()));
		}
		return "redirect:/connexion";
	}
	
	@RequestMapping(value="/reinitialisation", method=RequestMethod.GET)
	public String reinitialiser(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "reinitialiser";
	}
	
	@RequestMapping(value={ "/", "/accueil**" }, method = RequestMethod.GET)
	public String accueil() {
		return "redirect:/accueil";
	}
	
	@RequestMapping("/403")
	public String accesDenied() {
		return "403";
	}
}
