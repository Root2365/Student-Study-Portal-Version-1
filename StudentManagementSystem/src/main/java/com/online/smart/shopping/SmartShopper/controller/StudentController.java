
package com.online.smart.shopping.SmartShopper.controller;


import com.online.smart.shopping.SmartShopper.dao.*;
import com.online.smart.shopping.SmartShopper.entity.*;
import com.online.smart.shopping.SmartShopper.model.YouTubeVideo;
import com.online.smart.shopping.SmartShopper.model.YoutubeSearchCriteria;
import com.online.smart.shopping.SmartShopper.service.YouTubeService;
import com.online.smart.shopping.SmartShopper.utils.FileUploadUtil;
import com.online.smart.shopping.SmartShopper.utils.Mail;
import com.online.smart.shopping.SmartShopper.utils.SimpleTextEmail;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;


@Controller
@Transactional
public class StudentController {

    @Autowired
    NotesDAO notesDAO;

    @Autowired
    HomeworkDAO homeworkDAO;

    @Autowired
    TodosDAO todosDAO;

    @Autowired
    EnquiryDAO enquiryDAO;

    @Autowired
    Mail mail;

    @Autowired
    SimpleTextEmail simpleTextEmail;

    @Autowired
    private YouTubeService youtubeService;

    @Autowired
    WikiDao wikiDao;

    @Autowired
    VideoDao videoDao;

    @GetMapping("/student/notes")
    public String getNotes(Model model) {
        System.out.println("Inside /student/notes");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername());

        model.addAttribute("notesdata", notesDAO.findByUserName(userDetails.getUsername()));
        model.addAttribute("userName", userDetails.getUsername());

        return "notes";
    }


    @GetMapping("/student/creatnotes")
    public String createNotes(Model model, @RequestParam String userName) {
        Notes notes= new Notes();
        System.out.println("Inside /student/creatnotes");
        model.addAttribute("notes", notes);
        model.addAttribute("edit", false);

        model.addAttribute("userName", userName);
        return "createnotes";
    }


    @PostMapping("/student/create/notes")
    public String submitForm(@ModelAttribute("account") Notes notes ) {
        System.out.println("In Register Done ");
        Date d= new Date();
        notes.setCreatedDate(d);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, 25);
        d = c.getTime();
        notes.setDueDate(d);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername());
        notes.setUserName(userDetails.getUsername());
        notesDAO.saveNotes(notes);
        return "redirect:/";
    }

    @GetMapping("/student/get/homework")
    public String getHomeWork(Model model) {
        System.out.println("Inside /student/get/homework");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername());
        model.addAttribute("homeworkdata", homeworkDAO.findByUserName(userDetails.getUsername()));
        model.addAttribute("userName", userDetails.getUsername());
        List<String> subjects = Arrays.asList("ENGLISH", "MATHS", "PHYSICS","GEO","HISTORY");
        model.addAttribute("subjects", subjects);
        List<String> listStatus = Arrays.asList("STARTED", "PENDING", "DONE");
        model.addAttribute("statuslist", listStatus);
        return "homework";
    }


    @GetMapping("/student/create/homework")
    public String createHomeWork(Model model, @RequestParam String userName) {
        Homework homework= new Homework();
        System.out.println("Inside /student/create/homework");
        model.addAttribute("homework", homework);
        model.addAttribute("edit", false);
       List<String> subjects = Arrays.asList("ENGLISH", "MATHS", "PHYSICS","GEO","HISTORY");
        model.addAttribute("subjects", subjects);
        model.addAttribute("userName", userName);
        return "createhomework";
    }





    @PostMapping("/student/create/homework")
    public String submitHomeWork(@ModelAttribute("homework") Homework homework ) {
        System.out.println("In Register Done "+homework);
        Date d= new Date();
        homework.setCreatedDate(d);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, 25);
        d = c.getTime();
        homework.setDueDate(d);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername());
        homework.setUserName(userDetails.getUsername());
        homework.setStatus("PENDING");
        homeworkDAO.saveNotes(homework);
        return "redirect:/";
    }

    @GetMapping("/student/delete/homework")
    public String deleteHomeWork(Model model, @RequestParam Integer homeworkId) {
        Homework homework= new Homework();
        System.out.println("Inside /student/delete/homework");
        homeworkDAO.deleteNotes(homeworkId);
        return "redirect:/";
    }

    @GetMapping("/student/update/homework/status")
    public String updateHomeStatusWork(Model model, @RequestParam Integer homeworkId , @RequestParam String status) {
        Homework homework= new Homework();
        System.out.println("Inside /student/update/homework/status");
        homeworkDAO.updateNotes(homeworkId,status);
        return "redirect:/";
    }
    /*@PostMapping("/add/{role}/account")
    public String submitForm(@ModelAttribute("account") Account account , @PathVariable String role) {
        System.out.println("In Register Done "+role);
        System.out.println(account.toString());
        account.setUserRole(role);
        account.setActive(true);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(account.getEncrytedPassword());
        account.setEncrytedPassword(encodedPassword);
        accountDAO.saveAccount(account);
        return "redirect:/";
    }*/


    @GetMapping("/student/get/enquiry")
    public String getEnquiryPage(Model model) {
        System.out.println("/student/get/enquiry");
        model.addAttribute("enquiry", new Enquiry());
        return "enquiry";
    }

    @PostMapping("/student/send/enquiry")
    public String sendEnquiry(@ModelAttribute("enquiry") Enquiry enquiry) throws MessagingException, UnsupportedEncodingException, EmailException {
        System.out.println("/student/get/enquiry");
        enquiry.setCreatedDate(new Date());
        enquiryDAO.saveEnquiry(enquiry);
        simpleTextEmail.sendEmail(enquiry.getEmail(),enquiry.getMessage(),enquiry.getSubject(),enquiry.getEmail(),enquiry.getName());
        return "redirect:/";
    }





    //starting page for YouTube api demo
    @RequestMapping(value = "/student/youtube", method=RequestMethod.GET)
    public String youtubeDemo(Model model) {
        //instantiate an empty address object
        YoutubeSearchCriteria youtubeSearchCriteria = new YoutubeSearchCriteria();

        //put the object in the model
        model.addAttribute("youtubeSearchCriteria", youtubeSearchCriteria);

        //get out
        return "youtubeDemo";
    }


    @RequestMapping(value = "/youtubeDemo", method=RequestMethod.POST)
    public String formSubmit(YoutubeSearchCriteria youtubeSearchCriteria, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            return "youtubeDemo";
        }

        //get the list of YouTube videos that match the search term
        List<YouTubeVideo> videos = youtubeService.fetchVideosByQuery(youtubeSearchCriteria.getQueryTerm());

        if (videos != null && videos.size() > 0) {
            model.addAttribute("numberOfVideos", videos.size());
        } else {
            model.addAttribute("numberOfVideos", 0);
        }

        //put it in the model
        model.addAttribute("videos", videos);

        //add the criteria to the model as well
        model.addAttribute("youtubeSearchCriteria", youtubeSearchCriteria);

        //get out
        return "showYoutubeResults";
    }

    @GetMapping("/student/get/wiki")
    public String getWikiPage(Model model,@RequestParam String keyword) {
        System.out.println("/student/get/wiki");
        int numberOfWikis=0;
        if((keyword!=null&& !keyword.isEmpty()&&!"null".equals(keyword))){
            List<Wiki> wikis=wikiDao.findByTitle(keyword);
            model.addAttribute("wikis",wikis);
            numberOfWikis= wikis.size();
        }
        model.addAttribute("numberOfWikis", numberOfWikis);
/*
        model.addAttribute("wiki", new Wiki());
*/
        return "wiki";
    }


    @GetMapping("/student/create/wiki")
    public String createWiki(Model model) {
        Wiki wiki= new Wiki();
        System.out.println("Inside /student/create/wiki");
        model.addAttribute("wiki", wiki);
        model.addAttribute("edit", false);
        return "createwiki";
    }

    @PostMapping("/student/create/wiki")
    public String createWiki(@ModelAttribute("wiki") Wiki wiki ) throws IOException {
        System.out.println("In /student/create/wiki "+wiki);
        Date d= new Date();
        String fileName = StringUtils.cleanPath(wiki.getThumbnail().getOriginalFilename());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("File Name is "+fileName);
        fileName=dtf.format(now)+fileName;
        System.out.println("File now  is "+fileName);

        wiki.setThumbnailLocation("/images/"+fileName);
        wiki.setCreatedDate(d);
        wikiDao.saveWiki(wiki);
        ClassLoader classLoader = getClass().getClassLoader();

        String uploadDir ="C:/Student J2EE project/StudentManagementSystem/StudentManagementSystem/src/main/resources/static/images";
        String uploadDir2 ="C:/Student J2EE project/StudentManagementSystem/StudentManagementSystem/target/classes/static/images";

        FileUploadUtil.saveFile(uploadDir, fileName, wiki.getThumbnail());
        FileUploadUtil.saveFile(uploadDir2, fileName, wiki.getThumbnail());

        return "redirect:/";
    }
    @GetMapping("/student/edit/wiki")
    public String editWiki(Model model,@RequestParam Integer id) {
        Wiki wiki =wikiDao.findWiki(id);
        System.out.println("Inside /student/edit/wiki");
        model.addAttribute("wiki", wiki);
        model.addAttribute("id", id);
        model.addAttribute("edit", true);
        return "createwiki";
    }

    @PostMapping("/student/edit/wiki/{id}")
    public String createWiki(@ModelAttribute("wiki") Wiki wiki,@PathVariable Integer id ) {
        System.out.println("In /student/create/wiki "+wiki);
        Wiki oldwiki =wikiDao.findWiki(id);
        oldwiki.setDescription(wiki.getDescription());
        oldwiki.setTitle(wiki.getTitle());
        oldwiki.setLink(wiki.getLink());
        oldwiki.setSmalldescription(wiki.getSmalldescription());
        wikiDao.saveWiki(oldwiki);

        return "redirect:/";
    }


    @GetMapping("/student/get/video")
    public String getVideosPage(Model model,@RequestParam(required = false) String keyword) {
        System.out.println("/student/get/video");
        int numberOfVideos=0;
        if((keyword!=null&& !keyword.isEmpty()&&!"null".equals(keyword))){
            List<Video> videos=videoDao.findByTitle(keyword);
            model.addAttribute("videos",videos);
            numberOfVideos= videos.size();
        }
        model.addAttribute("numberOfVideos", numberOfVideos);
/*
        model.addAttribute("wiki", new Wiki());
*/
        return "video";
    }


    @GetMapping("/student/create/video")
    public String createVideo(Model model) {
        Video video= new Video();
        System.out.println("Inside /student/create/video");
        model.addAttribute("video", video);
        model.addAttribute("edit", false);
        return "createvideo";
    }

    @PostMapping("/student/create/video")
    public String createWiki(@ModelAttribute("video") Video video ) throws IOException {
        System.out.println("In /student/create/video "+video);
        Date d= new Date();
        String fileName = StringUtils.cleanPath(video.getThumbnail().getOriginalFilename());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(" File Name is "+fileName);
        fileName=dtf.format(now)+fileName;
        System.out.println(" File now  is "+fileName);
        video.setThumbnailLocation("/images/"+fileName);
        video.setCreatedDate(d);
        videoDao.saveVideo(video);

        String uploadDir ="C:/Student J2EE project/StudentManagementSystem/StudentManagementSystem/src/main/resources/static/images";
        String uploadDir2 ="C:/Student J2EE project/StudentManagementSystem/StudentManagementSystem/target/classes/static/images";

        FileUploadUtil.saveFile(uploadDir, fileName, video.getThumbnail());
        FileUploadUtil.saveFile(uploadDir2, fileName, video.getThumbnail());

        return "redirect:/";
    }
    @GetMapping("/student/edit/video")
    public String editVideo(Model model,@RequestParam Integer id) {
        Video video =videoDao.findVideo(id);
        System.out.println("Inside /student/edit/video");
        model.addAttribute("video", video);
        model.addAttribute("id", id);
        model.addAttribute("edit", true);
        return "createvideo";
    }

    @PostMapping("/student/edit/video/{id}")
    public String createVideo(@ModelAttribute("wiki") Video video,@PathVariable Integer id ) {
        System.out.println("In /student/create/video "+video);
        Video oldVideo =videoDao.findVideo(id);
        oldVideo.setDescription(video.getDescription());
        oldVideo.setTitle(video.getTitle());
        oldVideo.setLink(video.getLink());
        videoDao.saveVideo(oldVideo);

        return "redirect:/";
    }

    @GetMapping("/student/video/view/description")
    public String editVideoDescription(Model model,@RequestParam Integer id) {
        Video video =videoDao.findVideo(id);
        System.out.println("Inside /student/video/view/description");
        model.addAttribute("video", video);
        model.addAttribute("id", id);
        model.addAttribute("edit", true);
        return "videodescription";
    }

    @GetMapping("/student/delete/video")
    public String deleteVideo(Model model,@RequestParam Integer id) {
        System.out.println("Inside   /student/delete/video");
        videoDao.deleteVideo(id);
        return "redirect:/";
    }

    @GetMapping("/student/delete/wiki")
    public String deleteWiki(Model model,@RequestParam Integer id) {
        System.out.println("Inside   /student/delete/video");
        wikiDao.deleteWiki(id);
        return "redirect:/";
    }

    @GetMapping("/student/delete/notes")
    public String deleteNotes(Model model,@RequestParam Integer id) {
        System.out.println("Inside   /student/delete/notes");
        notesDAO.deleteNotes(id);
        return "redirect:/";
    }

    @GetMapping("/student/edit/notes")
    public String editWiki(Model model,@RequestParam Integer id, @RequestParam String userName) {
        Notes notes =notesDAO.findNotes(id);
        System.out.println("Inside /student/edit/wiki");
        model.addAttribute("notes", notes);
        model.addAttribute("edit", true);
        model.addAttribute("id", notes.getId());

        model.addAttribute("userName", userName);
        return "createnotes";
    }

    @PostMapping("/student/edit/note/{id}")
    public String editNotes(@ModelAttribute("notes") Notes notes,@PathVariable Integer id ) {
        System.out.println("In /student/edit/notes/{id} "+notes);
        Notes oldNotes =notesDAO.findNotes(id);
        oldNotes.setDescription(notes.getDescription());
        oldNotes.setTitle(notes.getTitle());
        notesDAO.updateNotes(oldNotes);
        return "redirect:/";
    }

    @GetMapping("/student/get/todos")
    public String getTodos(Model model) {
        System.out.println("Inside /student/get/todos");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername());
        model.addAttribute("todosdata", todosDAO.findByUserName(userDetails.getUsername()));
        model.addAttribute("userName", userDetails.getUsername());
        return "todo";
    }


    @GetMapping("/student/create/todos")
    public String getTodos(Model model, @RequestParam String userName) {
        Todos todos= new Todos();
        System.out.println("Inside /student/create/todos");
        model.addAttribute("todos", todos);
        model.addAttribute("edit", false);
        model.addAttribute("userName", userName);
        return "createtodos";
    }





    @PostMapping("/student/create/todos")
    public String submitTodos(@ModelAttribute("todos") Todos todos ) {
        System.out.println("In Register Done "+todos);
        Date d= new Date();
        todos.setCreatedDate(d);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, 25);
        d = c.getTime();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername());
        todos.setUserName(userDetails.getUsername());
        todos.setFavourite("NO");
        todosDAO.saveTodos(todos);
        return "redirect:/";
    }

    @GetMapping("/student/delete/todos")
    public String deleteTodos(Model model, @RequestParam Integer todosId) {
        System.out.println("Inside /student/delete/todos");
        todosDAO.deleteTodos(todosId);
        return "redirect:/";
    }

    @GetMapping("/student/update/todos/favourite")
    public String updateFavouriteTodos(Model model, @RequestParam Integer todosId , @RequestParam String status) {
        Todos todos= new Todos();
        System.out.println("Inside /student/update/homework/status");
        todosDAO.updateTodos(todosId,status);
        return "redirect:/";
    }


}


