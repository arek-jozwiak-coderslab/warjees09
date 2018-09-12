package pl.coderslab.hero;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.storage.StorageService;

import javax.validation.Valid;

@Controller
@RequestMapping("/hero")
public class HeroController {

    private final HeroRepository heroRepository;
    private final StorageService storageService;

    public HeroController(HeroRepository heroRepository, StorageService storageService) {
        this.heroRepository = heroRepository;
        this.storageService = storageService;
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("heroes", heroRepository.findAll());
        return "hero/list";
    }

    @RequestMapping("/list-page")
    public String listPage(Model model, Pageable pageable) {
        model.addAttribute("page", heroRepository.findAll(pageable));
        return "hero/list-page";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable long id) {
        heroRepository.delete(id);
        return "redirect:/hero/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("hero", new Hero());
        return "hero/add";
    }

    @PostMapping("/add")
    public String addPersonPerform(@RequestParam("file") MultipartFile file, @ModelAttribute @Valid Hero hero, BindingResult result,
                                   RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "hero/add";
        }
        if (!file.isEmpty()) {
            storageService.store(file);

        }
        redirectAttributes.addFlashAttribute("message", "Hero dodany prawidłowo ");

        heroRepository.save(hero);
        return "redirect:/hero/list";
    }

}
