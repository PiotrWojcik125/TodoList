package todoList.lang;

import java.util.List;
import java.util.stream.Collectors;

class LangService {
    private LangRepository langRepository;

    LangService(){
        this(new LangRepository());
    }
    LangService(LangRepository langRepository){
        this.langRepository=langRepository;
    }
    List<LangDTO> findAll(){
        return langRepository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(Collectors.toList());
    }

}
