package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComment(@PathVariable("imageId")Integer imageId, @RequestParam("comment") String text,
                             @PathVariable("imageTitle")String title, HttpSession session) {
        User user = (User)session.getAttribute("loggeduser");
        Image image = imageService.getImage(imageId);
        Comment comment1 = new Comment();
        comment1.setText(text);
        comment1.setDate(new Date());
        comment1.setUser(user);
        comment1.setImage(image);
        commentService.addComment(comment1);
        return "redirect:/images/"+imageId+"/"+title;
        }
}
