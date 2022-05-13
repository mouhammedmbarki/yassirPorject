package com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.base


interface DomainDTOMappingService<Domain,DTO> {

    fun mapDomainToDTO(domain : Domain) : DTO

     fun mapDomainToDTO(domainlist : List<Domain>) : List<DTO> = domainlist.map { domain->
        mapDomainToDTO(domain)
    }
}