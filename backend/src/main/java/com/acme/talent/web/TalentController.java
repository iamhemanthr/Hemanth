package com.acme.talent.web;

import com.acme.talent.domain.Talent;
import com.acme.talent.domain.TalentStatus;
import com.acme.talent.dto.StatusUpdateRequest;
import com.acme.talent.dto.TalentDto;
import com.acme.talent.mapper.TalentMapper;
import com.acme.talent.repo.TalentRepository;
import com.acme.talent.service.TalentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/talents")
public class TalentController {
  private final TalentRepository repo;
  private final TalentService service;
  public TalentController(TalentRepository r, TalentService s){ this.repo=r; this.service=s; }

  @GetMapping
  public Page<TalentDto> search(@RequestParam(required=false) TalentStatus status,
                                @RequestParam(required=false) String skill,
                                @RequestParam(required=false) String location,
                                @RequestParam(required=false) Integer minExp,
                                @PageableDefault(size=20, sort="updatedAt") Pageable pageable){
    return service.search(status, skill, location, minExp, pageable).map(TalentMapper::toDto);
  }

  @GetMapping("/{id}")
  public TalentDto get(@PathVariable Long id){
    return repo.findById(id).map(TalentMapper::toDto).orElseThrow();
  }

  @PostMapping
  public TalentDto create(@Valid @RequestBody Talent t){
    return TalentMapper.toDto(repo.save(t));
  }

  @PutMapping("/{id}")
  public TalentDto update(@PathVariable Long id, @Valid @RequestBody Talent t){
    t.setId(id);
    return TalentMapper.toDto(repo.save(t));
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){ repo.deleteById(id); }

  @PostMapping("/{id}/status")
  public TalentDto status(@PathVariable Long id, @Valid @RequestBody StatusUpdateRequest req){
    return TalentMapper.toDto(service.updateStatus(id, req));
  }
}
