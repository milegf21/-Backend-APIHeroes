package co.udea.heroes.api.service;

import java.util.List;
//import java.util.Optional;

import co.udea.heroes.api.model.Hero;

public interface HeroService {
	
	public List<Hero> getHeroes();
	public Hero getHero(int id) ;
	
	public Hero getHero(int id, String name) ;
	
	public Hero searchHeroes(String term);
	
	public Hero updateHero(Hero hero);
	
	public Hero addHero(Hero hero);
	
	public void deleteHero(Hero hero);
}
