package dev.charles.web;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dev.charles.dao.LogementRepository;
import dev.charles.dao.OperationRepository;
import dev.charles.dao.ContactRepository;
import dev.charles.dao.DepartementRepository;
import dev.charles.dao.EntrepriseRepository;
import dev.charles.dao.MapRepository;
import dev.charles.dao.ModePaiementRepository;
import dev.charles.dao.ImageRepository;
import dev.charles.dao.PartenaireRepository;
import dev.charles.dao.PayeRepository;
import dev.charles.dao.RegionRepository;
import dev.charles.dao.RoleRepository;
import dev.charles.dao.TemoignageRepository;
import dev.charles.dao.TypeBienRepository;
import dev.charles.dao.UtilisateurRepository;
import dev.charles.dao.VideoRepository;
import dev.charles.dao.VilleRepository;
import dev.charles.entities.Contact;
import dev.charles.entities.Departement;
import dev.charles.entities.Entreprise;
import dev.charles.entities.Map;
import dev.charles.entities.ModePaiement;
import dev.charles.entities.Image;
import dev.charles.entities.Location;
import dev.charles.entities.Logement;
import dev.charles.entities.Partenaires;
import dev.charles.entities.Paye;
import dev.charles.entities.Region;
import dev.charles.entities.Role;
import dev.charles.entities.Temoignage;
import dev.charles.entities.TypeBien;
import dev.charles.entities.Utilisateur;
import dev.charles.entities.Vente;
import dev.charles.entities.Video;
import dev.charles.entities.Ville;

@Controller
public class ImmoController {
	@Autowired
	private PartenaireRepository partenaireRepository;
	@Autowired
	private TypeBienRepository typeBienRepository;
	@Autowired
	private RegionRepository regionRepository;
	@Autowired
	private DepartementRepository departementRepository;
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private LogementRepository logementRepository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private VideoRepository videoRepository;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private TemoignageRepository temoignageRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private MapRepository mapRepository;
	@Autowired
	private ModePaiementRepository modePaiementRepository;
	@Autowired
	private PayeRepository payeRepository;
	
	@Value("${dir.logoPartenaire}")
	private String logoPartenaireDir;
	@Value("${dir.logoEntreprise}")
	private String logoEntrepriseDir;
	@Value("${dir.imageLogement}")
	private String imageLogementDir;
	@Value("${dir.photo}")
	private String photoDir;
	@Value("${dir.logoPaiement}")
	private String logoPaiementDir;
	
	/////////////////////////////////////Partie Client///////////////////////////////////
	@RequestMapping(value="/getLogo", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getLogo(Long id) throws Exception {
		File f = new File(logoPartenaireDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/accueil")
	public String index(Model model) throws ParseException {
		Pageable topThree = new PageRequest(0,3);
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		List<Logement> listNouveaute = logementRepository.findByDateEnregistrement(df.parse("2019/03/29"), topThree);
		model.addAttribute("listePartenaires", partenaireRepository.findAll());
		model.addAttribute("listeTemoignages", temoignageRepository.findAll());
		model.addAttribute("nouveautes", listNouveaute);
		return "accueil";
	}
	
	@RequestMapping(value="/about")
	public String apropos() {
		return "about";
	}
	
	@RequestMapping(value="/getImage", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage(Long id) throws Exception {
		File f = new File(imageLogementDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/getPhoto", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhotos(Long id) throws Exception {
		File f = new File(photoDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/immo")
	public String logement(Model model, @RequestParam(name="nomVille", defaultValue="") String nomVille,
			@RequestParam(name="designation", defaultValue="") String designation,
			@RequestParam(name="etatLogement", defaultValue="") String nature,
			@RequestParam(name="nombrePieceLogement", defaultValue="2") int nombrePiece,
			@RequestParam(name="superficieLogement", defaultValue="") String superficie,
			@RequestParam(name="prixLogement", defaultValue="0.0") Double prix,
			@RequestParam(name="page", defaultValue="0") int p, 
			@RequestParam(name="size", defaultValue="5") int s) {
		Page<Logement> pageLogement = logementRepository.recherche(nomVille, designation, nature, nombrePiece, superficie, prix, new PageRequest(p, s));
		model.addAttribute("listeLogements", pageLogement.getContent());
		int[] pages = new int[pageLogement.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("ville", nomVille);
		model.addAttribute("typeBien", designation);
		model.addAttribute("nature", nature);
		model.addAttribute("nombrePieceLogement", nombrePiece);
		model.addAttribute("superficieLogement", superficie);
		model.addAttribute("prixLogement", prix);
		return "immo";
	}	
	
	@RequestMapping(value="/detail")
	public String detail(Long idLogement, Model model) {
		Logement l = logementRepository.getOne(idLogement);
		List<Image> listeImage = imageRepository.findByLogement(l);
		List<Video> listeVideo = videoRepository.findByLogement(l);
		List<Map> listeMap = mapRepository.findByLogement(l);
		model.addAttribute("listeImages", listeImage);
		model.addAttribute("listeVideos", listeVideo);
		model.addAttribute("listeMaps", listeMap);
		model.addAttribute("logement", l);
		return "detail";
	}
	
	@RequestMapping(value="/getLogos", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getLogos(Long id) throws Exception {
		File f = new File(logoEntrepriseDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/agence")
	public String agence(Model model, @RequestParam(name="motCle", defaultValue="") String motCle, @RequestParam(name="page", defaultValue="0") int p, @RequestParam(name="size", defaultValue="5") int s) {
		Page<Entreprise> pageEntreprise = entrepriseRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		model.addAttribute("listeEntreprises", pageEntreprise.getContent());
		int[] pages = new int[pageEntreprise.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "agence";
	}
	
	@RequestMapping(value="/condition")
	public String conditions() {
		return "condition";
	}
	
	@RequestMapping(value="/contact", method=RequestMethod.GET)
	public String formContact(Model model) {
		model.addAttribute("contact", new Contact());
		return "contact";
	}
	
	@RequestMapping(value="/traitement", method=RequestMethod.POST)
	public String saveContact(@Valid Contact c, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "contact";
		}
		contactRepository.save(c);
		return "redirect:accueil";
	}
	
	@RequestMapping(value="/operation", method=RequestMethod.GET)
	public String operation(Model model) {
		model.addAttribute("logements", logementRepository.findAll());
		model.addAttribute("utilisateurs", utilisateurRepository.findAll());
		model.addAttribute("listeModePaiements", modePaiementRepository.findAll());
		return "operation";
	}
	
	@RequestMapping(value="/saveOperation", method=RequestMethod.POST)
	public String saveOperation(Model model, String typeOperation,  @Valid Location l, @Valid Vente v) {
		try {
			if(typeOperation.equals("L")) {
				operationRepository.save(l);
			} else if(typeOperation.equals("V")) {
				operationRepository.save(v);
			} 
		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/operation?error="+e.getMessage();
		}
		return "redirect:/payement";
	}
	
	@RequestMapping(value="/getPaiement", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPaiement(Long id) throws Exception {
		File f = new File(logoPaiementDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/payement")
	public String paiement(Model model, @Valid Paye p, BindingResult bindingResult) {
		model.addAttribute("utilisateurs", utilisateurRepository.findAll());
		model.addAttribute("modePaiements", modePaiementRepository.findAll());
		model.addAttribute("operations", operationRepository.findAll());
		model.addAttribute("paye", new Paye());
		if(bindingResult.hasErrors()) {
			return "payement";
		}
		payeRepository.save(p);
		return "redirect:/confirmation";
	}
	
	@RequestMapping(value="/confirmation")
	public String confirmation(Model model) {
		return "confirmation";
	}
	///////////////////////////Utilisateur connecté////////////////////////////////////
	 @RequestMapping(value="/getLogedUser") 
	  public java.util.Map<String, Object> getLogedUser(HttpSession session){
		  SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		  String username = securityContext.getAuthentication().getName(); 
		  List<String> roles = new ArrayList<>(); 
		  for(GrantedAuthority ga : securityContext.getAuthentication().getAuthorities()) {
			  roles.add(ga.getAuthority()); 
		  } 
		  java.util.Map<String, Object> params = new HashMap<>(); 
		  params.put("username", username);
		  params.put("roles", roles); return params; 
	  }
	 
	///////////////////////////Partie administration///////////////////////////////////
	@RequestMapping(value="/admin/getLogoPartenaire", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getLogoPartenaire(Long id) throws Exception {
		File f = new File(logoPartenaireDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/admin/partenaire", method=RequestMethod.GET)
	public String formPartenaire(Model model) {
		model.addAttribute("partenaire", new Partenaires());
		return "partenaires";
	}
	
	@RequestMapping(value="/admin/ajoutPartenaire", method=RequestMethod.POST)
	public String addPartenaire(@Valid Partenaires p, BindingResult bindingResult, @RequestParam(name="picture") MultipartFile file) throws Exception {
		if(bindingResult.hasErrors()) {
			return "partenaires";
		}
		
		if(!(file.isEmpty())) {
			p.setLogoPartenaire(file.getOriginalFilename());
		}
		
		partenaireRepository.save(p);
		
		if(!(file.isEmpty())) {
			p.setLogoPartenaire(file.getOriginalFilename());
			file.transferTo(new File(logoPartenaireDir+p.getIdPartenaire()));
		}
		return "redirect:listePartenaire";
	}
	
	@RequestMapping(value="/admin/listePartenaire", method=RequestMethod.GET)
	public String listPartenaire(Model model, @RequestParam(name="motCle", defaultValue="") String motCle, @RequestParam(name="page", defaultValue="0") int p, @RequestParam(name="size", defaultValue="5") int s) {
		Page<Partenaires> pagePartenaires = partenaireRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		model.addAttribute("listePartenaires", pagePartenaires.getContent());
		int[] pages = new int[pagePartenaires.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "listePartenaire";
	}
	
	@RequestMapping(value="/admin/deletePartenaire")
	public String deletePartenaire(Long idPartenaire) {
		partenaireRepository.deleteById(idPartenaire);
		return "listePartenaire";
	}
	
	@RequestMapping(value="/admin/editePartenaire")
	public String editePartenaire(Long idPartenaire, Model model) {
		Partenaires p = partenaireRepository.getOne(idPartenaire);
		model.addAttribute("partenaire", p);
		return "editePartenaire";
	}
	
	@RequestMapping(value="/admin/updatePartenaire", method=RequestMethod.POST)
	public String updatePartenaire(@Valid Partenaires p, BindingResult bindingResult, @RequestParam(name="picture") MultipartFile file) throws Exception {
		if(bindingResult.hasErrors()) {
			return "editePartenaire";
		}
		
		if(!(file.isEmpty())) {
			p.setLogoPartenaire(file.getOriginalFilename());
		}
		
		partenaireRepository.save(p);
		
		if(!(file.isEmpty())) {
			p.setLogoPartenaire(file.getOriginalFilename());
			file.transferTo(new File(logoPartenaireDir+p.getIdPartenaire()));
		}
		
		return "redirect:listePartenaire";
	}
	
	@RequestMapping(value="/admin/typeBien", method=RequestMethod.GET)
	public String formTypeBien(Model model) {
		model.addAttribute("typeBien", new TypeBien());
		return "typeBien";
	}
	
	@RequestMapping(value="/admin/ajoutTypeBien", method=RequestMethod.POST)
	public String saveTypeBien(@Valid TypeBien tb, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "typeBien";
		}
		typeBienRepository.save(tb);
		return "redirect:listeTypeBien";
	}
	
	@RequestMapping(value="/admin/listeTypeBien", method=RequestMethod.GET)
	public String listTypeBien(Model model, @RequestParam(name="motCle", defaultValue="") String motCle, @RequestParam(name="page", defaultValue="0") int p, @RequestParam(name="size", defaultValue="5") int s) {
		Page<TypeBien> pageTypeBien = typeBienRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		model.addAttribute("listeTypeBien", pageTypeBien.getContent());
		int[] pages = new int[pageTypeBien.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "listeTypeBien";
	}
	
	@RequestMapping(value="/admin/deleteTypeBien")
	public String deleteTypeBien(Long idTypeBien) {
		typeBienRepository.deleteById(idTypeBien);
		return "redirect:listeTypeBien";
	}
	
	@RequestMapping(value="/admin/editeTypeBien")
	public String editTypeBien(Long idTypeBien, Model model) {
		TypeBien t = typeBienRepository.getOne(idTypeBien);
		model.addAttribute("typeBien", t);
		return "editeTypeBien";
	}
	
	@RequestMapping(value="/admin/updateTypeBien", method=RequestMethod.POST)
	public String updateTypeBien(@Valid TypeBien t, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "editeTypeBien";
		}
		
		typeBienRepository.save(t);
		
		return "redirect:listeTypeBien";
	}
	
	@RequestMapping(value="/admin/region", method=RequestMethod.GET)
	public String formRegion(Model model) {
		model.addAttribute("region", new Region());
		return "region";
	}
	
	@RequestMapping(value="/admin/ajoutRegion", method=RequestMethod.POST)
	public String saveRegion(@Valid Region r, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "region";
		}
		regionRepository.save(r);
		return "redirect:listeRegion";
	}
	
	@RequestMapping(value="/admin/listeRegion", method=RequestMethod.GET)
	public String listRegion(Model model, @RequestParam(name="motCle", defaultValue="") String motCle, @RequestParam(name="page", defaultValue="0") int p, @RequestParam(name="size", defaultValue="5") int s) {
		Page<Region> pageRegion = regionRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		model.addAttribute("listeRegion", pageRegion.getContent());
		int[] pages = new int[pageRegion.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "listeRegion";
	}
	
	@RequestMapping(value="/admin/deleteRegion")
	public String deleteRegion(Long idRegion) {
		regionRepository.deleteById(idRegion);
		return "listeRegion";
	}
	
	@RequestMapping(value="/admin/editeRegion")
	public String editeRegion(Long idRegion, Model model) {
		Region r = regionRepository.getOne(idRegion);
		model.addAttribute("region", r);
		return "editeRegion";
	}
	
	@RequestMapping(value="/admin/updateRegion", method=RequestMethod.POST)
	public String updateRegion(@Valid Region r, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "editeRegion";
		}
		
		regionRepository.save(r);
		
		return "redirect:listeRegion";
	}
	
	@RequestMapping(value="/admin/departement", method=RequestMethod.GET)
	public String formDepartement(Model model) {
		model.addAttribute("regions", regionRepository.findAll());
		model.addAttribute("departement", new Departement());
		return "departement";
	}
	
	@RequestMapping(value="/admin/ajoutDepartement", method=RequestMethod.POST)
	public String saveDepartement(@Valid Departement d, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "departement";
		}
		departementRepository.save(d);
		return "redirect:listeDepartement";
	}
	
	@RequestMapping(value="/admin/listeDepartement", method=RequestMethod.GET)
	public String listDepartement(Model model, @RequestParam(name="motCle", defaultValue="") String motCle, @RequestParam(name="page", defaultValue="0") int p, @RequestParam(name="size", defaultValue="5") int s) {
		Page<Departement> pageDepartement = departementRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		model.addAttribute("listeDepartement", pageDepartement.getContent());
		int[] pages = new int[pageDepartement.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "listeDepartement";
	}
	
	@RequestMapping(value="/admin/deleteDepartement")
	public String deleteDepartement(Long idDepartement) {
		departementRepository.deleteById(idDepartement);
		return "listeDepartement";
	}
	
	@RequestMapping(value="/admin/editeDepartement")
	public String editeDepartement(Long idDepartement, Model model) {
		Departement d = departementRepository.getOne(idDepartement);
		model.addAttribute("regions", regionRepository.findAll());
		model.addAttribute("departement", d);
		return "editeDepartement";
	}
	
	@RequestMapping(value="/admin/updateDepartement", method=RequestMethod.POST)
	public String updateRegion(@Valid Departement d, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "editeDepartement";
		}
		
		departementRepository.save(d);
		
		return "redirect:listeDepartement";
	}
	
	@RequestMapping(value="/admin/ville", method=RequestMethod.GET)
	public String formVille(Model model) {
		model.addAttribute("departements", departementRepository.findAll());
		model.addAttribute("ville", new Ville());
		return "ville";
	}
	
	@RequestMapping(value="/admin/ajoutVille", method=RequestMethod.POST)
	public String saveVille(@Valid Ville v, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "ville";
		}
		villeRepository.save(v);
		return "redirect:listeVille";
	}
	
	@RequestMapping(value="/admin/listeVille", method=RequestMethod.GET)
	public String listVille(Model model, @RequestParam(name="motCle", defaultValue="") String motCle, @RequestParam(name="page", defaultValue="0") int p, @RequestParam(name="size", defaultValue="10") int s) {
		Page<Ville> pageVille = villeRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		model.addAttribute("listeVille", pageVille.getContent());
		int[] pages = new int[pageVille.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "listeVille";
	}
	
	@RequestMapping(value="/admin/deleteVille")
	public String deleteVille(Long idVille) {
		villeRepository.deleteById(idVille);
		return "listeVille";
	}
	
	@RequestMapping(value="/admin/editeVille")
	public String editeVille(Long idVille, Model model) {
		Ville v = villeRepository.getOne(idVille);
		model.addAttribute("departements", departementRepository.findAll());
		model.addAttribute("ville", v);
		return "editeVille";
	}
	
	@RequestMapping(value="/admin/updateVille", method=RequestMethod.POST)
	public String updateVille(@Valid Ville v, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "editeVille";
		}
		
		villeRepository.save(v);
		
		return "redirect:listeVille";
	}
	
	@RequestMapping(value="/admin/getLogoEntreprise", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getLogoEntreprise(Long id) throws Exception {
		File f = new File(logoEntrepriseDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/admin/entreprise", method=RequestMethod.GET)
	public String formEntreprise(Model model) {
		model.addAttribute("villes", villeRepository.findAll());
		model.addAttribute("entreprise", new Entreprise());
		return "entreprise";
	}
	
	@RequestMapping(value="/admin/ajoutEntreprise", method=RequestMethod.POST)
	public String saveEntreprise(@Valid Entreprise e, BindingResult bindingResult, @RequestParam(name="picture") MultipartFile file) throws Exception {
		if(bindingResult.hasErrors()) {
			return "entreprise";
		}
		
		if(!(file.isEmpty())) {
			e.setLogoEntreprise(file.getOriginalFilename());
		}
		
		entrepriseRepository.save(e);
		
		if(!(file.isEmpty())) {
			e.setLogoEntreprise(file.getOriginalFilename());
			file.transferTo(new File(logoEntrepriseDir+e.getIdEntreprise()));
		}
		return "redirect:listeEntreprise";
	}
	
	@RequestMapping(value="/admin/listeEntreprise", method=RequestMethod.GET)
	public String listEntreprise(Model model, @RequestParam(name="motCle", defaultValue="") String motCle, @RequestParam(name="page", defaultValue="0") int p, @RequestParam(name="size", defaultValue="5") int s) {
		Page<Entreprise> pageEntreprise = entrepriseRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		model.addAttribute("listeEntreprise", pageEntreprise.getContent());
		int[] pages = new int[pageEntreprise.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "listeEntreprise";
	}
	
	@RequestMapping(value="/admin/deleteEntreprise")
	public String deleteEntreprise(Long idEntreprise) {
		entrepriseRepository.deleteById(idEntreprise);
		return "listeEntreprise";
	}
	
	@RequestMapping(value="/admin/editeEntreprise")
	public String editeEntreprise(Long idEntreprise, Model model) {
		Entreprise e = entrepriseRepository.getOne(idEntreprise);
		model.addAttribute("villes", villeRepository.findAll());
		model.addAttribute("entreprise", e);
		return "editeEntreprise";
	}
	
	@RequestMapping(value="/admin/updateEntreprise", method=RequestMethod.POST)
	public String updateEntreprise(@Valid Entreprise e, BindingResult bindingResult, @RequestParam(name="picture") MultipartFile file) throws Exception {
		if(bindingResult.hasErrors()) {
			return "editeEntreprise";
		}
		
		if(!(file.isEmpty())) {
			e.setLogoEntreprise(file.getOriginalFilename());
		}
		
		entrepriseRepository.save(e);
		
		if(!(file.isEmpty())) {
			e.setLogoEntreprise(file.getOriginalFilename());
			file.transferTo(new File(logoEntrepriseDir+e.getIdEntreprise()));
		}
		
		return "redirect:listeEntreprise";
	}
	
	@RequestMapping(value="/admin/getPhoto", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception {
		File f = new File(photoDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/admin/logement", method=RequestMethod.GET)
	public String formLogement(Model model) {
		model.addAttribute("entreprises",entrepriseRepository.findAll());
		model.addAttribute("typeBiens",typeBienRepository.findAll());
		model.addAttribute("villes",villeRepository.findAll());
		model.addAttribute("logement", new Logement());
		return "logement";
	}
	
	@RequestMapping(value="/admin/ajoutLogement", method=RequestMethod.POST)
	public String saveLogement(@Valid Logement l, BindingResult bindingResult, @RequestParam(name="picture") MultipartFile file) throws Exception {
		if(bindingResult.hasErrors()) {
			return "logement";
		}
		
		if(!(file.isEmpty())) {
			l.setPhotoLogement(file.getOriginalFilename());
		}
		
		logementRepository.save(l);
		
		if(!(file.isEmpty())) {
			l.setPhotoLogement(file.getOriginalFilename());
			file.transferTo(new File(photoDir+l.getIdLogement()));
		}
		return "redirect:listeLogement";
	}
	
	@RequestMapping(value="/admin/listeLogement", method=RequestMethod.GET)
	public String listLogement(Model model, @RequestParam(name="motCle", defaultValue="") String motCle, @RequestParam(name="page", defaultValue="0") int p, @RequestParam(name="size", defaultValue="10") int s) {
		Page<Logement> pageLogement = logementRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		model.addAttribute("listeLogement", pageLogement.getContent());
		int[] pages = new int[pageLogement.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "listeLogement";
	}
	
	@RequestMapping(value="/admin/deleteLogement")
	public String deleteLogement(Long idLogement) {
		logementRepository.deleteById(idLogement);
		return "listeLogement";
	}
	
	@RequestMapping(value="/admin/editeLogement")
	public String editeLogement(Long idLogement, Model model) {
		Logement l = logementRepository.getOne(idLogement);
		model.addAttribute("entreprises",entrepriseRepository.findAll());
		model.addAttribute("typeBiens",typeBienRepository.findAll());
		model.addAttribute("villes",villeRepository.findAll());
		model.addAttribute("logement", l);
		return "editeLogement";
	}
	
	@RequestMapping(value="/admin/updateLogement", method=RequestMethod.POST)
	public String updateLogement(@Valid Logement l, BindingResult bindingResult, @RequestParam(name="picture") MultipartFile file) throws Exception {
		if(bindingResult.hasErrors()) {
			return "editeLogement";
		}
		
		if(!(file.isEmpty())) {
			l.setPhotoLogement(file.getOriginalFilename());
		}
		
		logementRepository.save(l);
		
		if(!(file.isEmpty())) {
			l.setPhotoLogement(file.getOriginalFilename());
			file.transferTo(new File(photoDir+l.getIdLogement()));
		}
		
		return "redirect:listeLogement";
	}
	
	@RequestMapping(value="/admin/getImageLogement", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImageLogement(Long id) throws Exception {
		File f = new File(imageLogementDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/admin/image", method=RequestMethod.GET)
	public String formImage(Model model) {
		model.addAttribute("logements", logementRepository.findAll());
		model.addAttribute("image", new Image());
		return "image";
	}
	
	@RequestMapping(value="/admin/ajoutImage", method=RequestMethod.POST)
	public String saveImage(@Valid Image i, BindingResult bindingResult, @RequestParam(name="picture") MultipartFile file) throws Exception {
		if(bindingResult.hasErrors()) {
			return "image";
		}
		
		if(!(file.isEmpty())) {
			i.setImage(file.getOriginalFilename());
		}
		
		imageRepository.save(i);
		
		if(!(file.isEmpty())) {
			i.setImage(file.getOriginalFilename());
			file.transferTo(new File(imageLogementDir+i.getIdImage()));
		}
		return "redirect:listeImage";
	}
	
	@RequestMapping(value="/admin/listeImage", method=RequestMethod.GET)
	public String listImage(Model model, @RequestParam(name="motCle", defaultValue="") String motCle, @RequestParam(name="page", defaultValue="0") int p, @RequestParam(name="size", defaultValue="10") int s) {
		Page<Image> pageImage = imageRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		model.addAttribute("listeImage", pageImage.getContent());
		int[] pages = new int[pageImage.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "listeImage";
	}
	
	@RequestMapping(value="/admin/deleteImage")
	public String deleteImage(Long idImage) {
		imageRepository.deleteById(idImage);
		return "listeImage";
	}
	
	@RequestMapping(value="/admin/editeImage")
	public String editeImage(Long idImage, Model model) {
		Image i = imageRepository.getOne(idImage);
		model.addAttribute("logements", logementRepository.findAll());
		model.addAttribute("image", i);
		return "editeImage";
	}
	
	@RequestMapping(value="/admin/updateImage", method=RequestMethod.POST)
	public String updateImage(@Valid Image i, BindingResult bindingResult, @RequestParam(name="picture") MultipartFile file) throws Exception {
		if(bindingResult.hasErrors()) {
			return "editeImage";
		}
		
		if(!(file.isEmpty())) {
			i.setImage(file.getOriginalFilename());
		}
		
		imageRepository.save(i);
		
		if(!(file.isEmpty())) {
			i.setImage(file.getOriginalFilename());
			file.transferTo(new File(imageLogementDir+i.getIdImage()));
		}
		
		return "redirect:listeImage";
	}
	
	@RequestMapping(value="/admin/role", method=RequestMethod.GET)
	public String formRole(Model model) {
		model.addAttribute("role", new Role());
		return "role";
	}
	
	@RequestMapping(value="/admin/ajoutRole", method=RequestMethod.POST)
	public String saveRole(@Valid Role r, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "role";
		}
		roleRepository.save(r);
		return "redirect:listeRole";
	}
	
	@RequestMapping(value="/admin/listeRole", method=RequestMethod.GET)
	public String listRole(Model model) {
		model.addAttribute("listeRole", roleRepository.findAll());
		return "listeRole";
	}
	
	@RequestMapping(value="/admin/deleteRole")
	public String deleteRole(String role) {
		roleRepository.deleteById(role);
		return "listeRole";
	}
	
	@RequestMapping(value="/admin/temoignage", method=RequestMethod.GET)
	public String formTemoignage(Model model) {
		model.addAttribute("temoignage", new Temoignage());
		model.addAttribute("utilisateurs", utilisateurRepository.findAll());
		return "temoignage";
	}
	
	@RequestMapping(value="/admin/ajoutTemoignage", method=RequestMethod.POST)
	public String saveTemoignage(@Valid Temoignage t, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "temoignage";
		}
		temoignageRepository.save(t);
		return "redirect:listeTemoignage";
	}
	
	@RequestMapping(value="/admin/listeTemoignage", method=RequestMethod.GET)
	public String listTemoignage(Model model) {
		model.addAttribute("listeTemoignage", temoignageRepository.findAll());
		return "listeTemoignage";
	}
	
	@RequestMapping(value="/admin/deleteTemoignage")
	public String deleteTemoignage(Long idTemoignage) {
		temoignageRepository.deleteById(idTemoignage);
		return "listeTemoignage";
	}
	
	@RequestMapping(value="/admin/editeTemoignage")
	public String editeTemoignage(Long idTemoignage, Model model) {
		Temoignage t = temoignageRepository.getOne(idTemoignage);
		model.addAttribute("temoignage", t);
		return "editeTemoignage";
	}
	
	@RequestMapping(value="/admin/updateTemoignage", method=RequestMethod.POST)
	public String updateTemoignage(@Valid Temoignage t, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "editeTemoignage";
		}
		
		temoignageRepository.save(t);
		
		return "redirect:listeTemoignage";
	}
	
	@RequestMapping(value="/admin/listeUtilisateur", method=RequestMethod.GET)
	public String listUtilisateur(Model model, @RequestParam(name="motCle", defaultValue="") String motCle, @RequestParam(name="page", defaultValue="0") int p, @RequestParam(name="size", defaultValue="5") int s) {
		Page<Utilisateur> pageUtilisateur = utilisateurRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		model.addAttribute("listeUtilisateur", pageUtilisateur.getContent());
		int[] pages = new int[pageUtilisateur.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "listeUtilisateur";
	}
	
	@RequestMapping(value="/admin/deleteUtilisateur")
	public String deleteUtilisateur(String username) {
		utilisateurRepository.deleteById(username);
		return "listeUtilisateur";
	}
	
	@RequestMapping(value="/admin/listeContact", method=RequestMethod.GET)
	public String listContact(Model model, @RequestParam(name="motCle", defaultValue="") String motCle, @RequestParam(name="page", defaultValue="0") int p, @RequestParam(name="size", defaultValue="5") int s) {
		Page<Contact> pageContact = contactRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		model.addAttribute("listeContact", pageContact.getContent());
		int[] pages = new int[pageContact.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "listeContact";
	}
	
	@RequestMapping(value="/admin/deleteContact")
	public String deleteContact(Long idContact) {
		contactRepository.deleteById(idContact);
		return "listeContact";
	}
	
	@RequestMapping(value="/admin/video", method=RequestMethod.GET)
	public String formVideo(Model model) {
		model.addAttribute("logements", logementRepository.findAll());
		model.addAttribute("video", new Video());
		return "video";
	}
	
	@RequestMapping(value="/admin/ajoutVideo", method=RequestMethod.POST)
	public String saveVideo(@Valid Video v, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "video";
		}
		
		videoRepository.save(v);
		
		return "redirect:listeVideo";
	}
	
	@RequestMapping(value="/admin/listeVideo", method=RequestMethod.GET)
	public String listVideo(Model model, @RequestParam(name="motCle", defaultValue="") String motCle, @RequestParam(name="page", defaultValue="0") int p, @RequestParam(name="size", defaultValue="10") int s) {
		Page<Video> pageVideo = videoRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		model.addAttribute("listeVideo", pageVideo.getContent());
		int[] pages = new int[pageVideo.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "listeVideo";
	}
	
	@RequestMapping(value="/admin/deleteVideo")
	public String deleteVideo(Long idVideo) {
		videoRepository.deleteById(idVideo);
		return "listeVideo";
	}
	
	@RequestMapping(value="/admin/map", method=RequestMethod.GET)
	public String formMap(Model model) {
		model.addAttribute("logements", logementRepository.findAll());
		model.addAttribute("map", new Map());
		return "map";
	}
	
	@RequestMapping(value="/admin/ajoutMap", method=RequestMethod.POST)
	public String saveMap(@Valid Map m, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "map";
		}
		
		mapRepository.save(m);
		
		return "redirect:listeMap";
	}
	
	@RequestMapping(value="/admin/listeMap", method=RequestMethod.GET)
	public String listMap(Model model) {
		model.addAttribute("listeMap", mapRepository.findAll());
		return "listeMap";
	}
	
	@RequestMapping(value="/admin/getLogoPaiement", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getLogoPaiement(Long id) throws Exception {
		File f = new File(logoPaiementDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/admin/modePaiement", method=RequestMethod.GET)
	public String formmodePaiement(Model model) {
		model.addAttribute("modePaiement", new ModePaiement());
		return "modePaiement";
	}
	
	@RequestMapping(value="/admin/ajoutModePaiement", method=RequestMethod.POST)
	public String saveModePaiement(@Valid ModePaiement m, BindingResult bindingResult, @RequestParam(name="picture") MultipartFile file) throws Exception {
		if(bindingResult.hasErrors()) {
			return "modePaiement";
		}
		
		if(!(file.isEmpty())) {
			m.setLogoPaiement(file.getOriginalFilename());
		}
		
		modePaiementRepository.save(m);
		
		if(!(file.isEmpty())) {
			m.setLogoPaiement(file.getOriginalFilename());
			file.transferTo(new File(logoPaiementDir+m.getIdModePaiement()));
		}
		return "redirect:listeModePaiement";
	}
	
	@RequestMapping(value="/admin/listeModePaiement", method=RequestMethod.GET)
	public String listModePaiement(Model model) {
		model.addAttribute("listeModePaiement", modePaiementRepository.findAll());
		return "listeModePaiement";
	}
	
	@RequestMapping(value="/admin/deleteModePaiement")
	public String deleteModePaiment(Long idModePaiement) {
		modePaiementRepository.deleteById(idModePaiement);
		return "listeModePaiement";
	}
}
