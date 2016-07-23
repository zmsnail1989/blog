package com.snail.architecture.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.snail.architecture.entity.Resource;
import com.snail.architecture.entity.Resource.ResourceType;
import com.snail.architecture.service.ResourceService;

/**
 * <p>User: Snail
 * <p>Date: 14-9-14
 * <p>Version: 1.0
 */
@Controller
@RequestMapping("/backend/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ModelAttribute("types")
    public Resource.ResourceType[] resourceTypes() {
        return Resource.ResourceType.values();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
        return "/backend/admin-resource";
    }

    @RequestMapping(value = "/appendParent", method = RequestMethod.GET)
    public String showAppendParentForm(Model model) {
        Resource parent = new Resource();
        model.addAttribute("resource", parent);
        model.addAttribute("op", "新增父节点");
        return "/backend/admin-resource-parentedit";
    }
    @RequestMapping(value = "/appendParent", method = RequestMethod.POST)
    public String createParent(Resource resource, RedirectAttributes redirectAttributes) {
    	resource.setType(ResourceType.menu);
    	resourceService.createResource(resource);
        redirectAttributes.addFlashAttribute("msg", "新增父节点成功");
        return "redirect:/backend/resource";
    }
    
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
    public String showAppendChildForm(@PathVariable("parentId") Long parentId, Model model) {
        Resource parent = resourceService.findOne(parentId);
        model.addAttribute("parent", parent);
        Resource child = new Resource();
        child.setParentId(parentId);
        child.setParentIds(parent.makeSelfAsParentIds());
        model.addAttribute("resource", child);
        model.addAttribute("op", "新增子节点");
        return "/backend/admin-resource-childedit";
    }

    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.POST)
    public String createChild(Resource resource, RedirectAttributes redirectAttributes) {
        resourceService.createResource(resource);
        redirectAttributes.addFlashAttribute("msg", "新增子节点成功");
        return "redirect:/backend/resource";
    }
    
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("resource", resourceService.findOne(id));
        model.addAttribute("op", "修改");
        return "/backend/admin-resource-childedit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(Resource resource, RedirectAttributes redirectAttributes) {
        resourceService.updateResource(resource);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/backend/resource";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        resourceService.deleteResource(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/backend/resource";
    }


}
