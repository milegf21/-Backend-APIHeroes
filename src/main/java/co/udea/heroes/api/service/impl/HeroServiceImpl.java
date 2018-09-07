package co.udea.heroes.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.udea.heroes.api.exception.DataNotFoundException;
import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.repository.HeroRepository;
import co.udea.heroes.api.service.HeroService;
import co.udea.heroes.api.util.Messages;


@Service
public class HeroServiceImpl implements HeroService {
	
	private final Logger log = LoggerFactory.getLogger(HeroServiceImpl.class);
	
	private Messages messages;	
	private HeroRepository heroRepository;

	public HeroServiceImpl(HeroRepository heroRepository, Messages messages) {
		this.heroRepository = heroRepository;
		this.messages = messages;
	}

	@Override
	public List<Hero> getHeroes() {
		log.debug("Inicio getHeroes");
		List<Hero> heroes= heroRepository.findAll();
		log.debug("Fin getHeroes");
		return heroes;
	}
	
	@Override
	public Hero getHero(int id) {
		log.debug("Inicio getHero: id = {}", id);
		Optional<Hero> hero = heroRepository.findById(id);
		if(!hero.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.hero"));
		}
		log.debug("Fin getHero: heroe = {}", hero.get());
		return hero.get();
	}
	
	@Override
	public Hero getHero(int id, String name) {
		log.debug("Inicio getHero: id = {}", id);
		Optional<Hero> hero = heroRepository.findById(id);
		if(!hero.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.hero"));
		}
		log.debug("Fin getHero: heroe = {}", hero.get());
		return hero.get();
	}
	
	@Override
	public Hero searchHeroes(String term) {
		log.debug("Inicio getHero: id = {}", term);
		Optional<Hero> hero = heroRepository.findByName(term);
		if(!hero.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.hero"));
		}
		log.debug("Fin getHero: heroe = {}", hero.get());
		return hero.get();
	}
	
	@Override
	public Hero updateHero(Hero newHero) {
		log.debug("Inicio updateHero: id = {}", newHero);
		return heroRepository.save(newHero);
	}
	
	@Override
	public Hero addHero(Hero hero) {
		log.debug("Inicio addHero: id = {}", hero);
		return heroRepository.save(hero);
	}
	
	@Override
	public void deleteHero(Hero hero ) {
		log.debug("Inicio deleteHero: id = {}", hero);
		heroRepository.delete(hero);
	}
	
}
