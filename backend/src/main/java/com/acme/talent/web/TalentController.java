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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/talents")
public class TalentController {

  private final TalentRepository repo;
  private final TalentService service;

  public TalentController(TalentRepository repo, TalentService service) {
    this.repo = repo;
    this.service = service;
  }

  @GetMapping
  public Page<TalentDto> search(
    @RequestParam(name = "status", required = false) TalentStatus status,
    @RequestParam(name = "skill", required = false) String skill,
    @RequestParam(name = "location", required = false) String location,
    @RequestParam(name = "minExp", required = false) Integer minExp,
    @PageableDefault(size = 20, sort = "updatedAt") Pageable pageable) {

    return service.search(status, skill, location, minExp, pageable)
      .map(TalentMapper::toDto);
  }

  @GetMapping("/{id}")
  public TalentDto get(@PathVariable Long id) {
    return repo.findById(id)
      .map(TalentMapper::toDto)
      .orElseThrow();
  }

  @PostMapping
  public TalentDto create(@Valid @RequestBody Talent t) {
    return TalentMapper.toDto(repo.save(t));
  }

  @PutMapping("/{id}")
  public TalentDto update(@PathVariable Long id, @Valid @RequestBody Talent t) {
    t.setId(id);
    return TalentMapper.toDto(repo.save(t));
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    repo.deleteById(id);
  }

  @PostMapping("/{id}/status")
  public TalentDto status(@PathVariable Long id,
                          @Valid @RequestBody StatusUpdateRequest req) {
    return TalentMapper.toDto(service.updateStatus(id, req));
  }
}
