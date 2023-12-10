package com.webApp.MyFirstWebApp.Todo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes({"name", "id"})
public class TodoControllerJpa {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@RequestMapping("/list-todos")
	public String todoList(ModelMap model){
		String username = getLoggedInUserName();
		List<Todo> todos = todoRepository.findByUsername(username);
//		List<Todo> todos = todoService.getTodoByName(userName);
		model.put("todos", todos);
		return "listTodos";
	}
	
	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model){
		String userName = (String)model.get("name");
		Todo todo = new Todo(0, userName,"",LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result){
		if(result.hasErrors()) {
			return "todo";
		}
		
		String username = (String)model.get("name");
		todo.setUsername(username);
		todoRepository.save(todo);
		
//		todoService.addTodo((String)model.get("name"), todo.getDescription(), todo.getTargetDate(), todo.isDone());
		
		return "redirect:list-todos";
	}
	
	@RequestMapping(value = "delete-todo")
	public String deleteTodo(@RequestParam int id){
		todoRepository.deleteById(id);
//		todoService.deleteTodo(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value = "update-todo")
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		
		Todo todo = todoRepository.findById(id).get();
		model.put("todo", todo);
		return "todo";
	
	}
	
	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result){
		if(result.hasErrors()) {
			return "todo";
		}
		
//		todoService.updateTodo(todo);
		
		todo.setUsername(getLoggedInUserName());
		todoRepository.save(todo);
		
		return "redirect:list-todos";
	}
	
	public String getLoggedInUserName() {
		
		return SecurityContextHolder.getContext().getAuthentication().getName();
		
	}
	
	
}
