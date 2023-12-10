package com.webApp.MyFirstWebApp.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList<>();
	private static int index =0; 
	
	static {
		todos.add(new Todo(++index, "Pranshu", "Learn Trading", LocalDate.now().plusYears(1), false));
		
		todos.add(new Todo(++index, "Pranshu", "Learn Renting", LocalDate.now().plusYears(1), false));
		
		todos.add(new Todo(++index, "Pranshu", "Become Millionare", LocalDate.now().plusYears(1), false));
	}
	
	public List<Todo> getTodoByName(String name){
		List<Todo> list = new ArrayList<>();
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(name);
		
		return todos.stream().filter(predicate).toList(); 
	}

	public void addTodo(String name, String description, LocalDate targetDate, boolean done) {
		// TODO Auto-generated method stub
	
		Todo todo = new Todo(++index, name, description, targetDate, done);
		
		todos.add(todo);
	}
	
	public void deleteTodo(int id) {
	    
//		int idx = 0;
//		
//		for(int i=0; i<todos.size(); i++) {
//			if(todos.get(i).getId() == id) {
//				idx = i;
//				break;
//			}
//		}
//		todos.remove(idx);
		
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		
		todos.removeIf(predicate );
	}
	
	public void updateTodo(@Valid Todo todo) {
		// TODO Auto-generated method stub
		int id = todo.getId();
		
		for(int i=0; i<todos.size(); i++) {
			if(todos.get(i).getId() == id) {
				todos.get(i).setDescription(todo.getDescription());
				todos.get(i).setTargetDate(todo.getTargetDate());
			}
		}
	}

	public Todo findById(int id) {
		// TODO Auto-generated method stub
		
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

}
