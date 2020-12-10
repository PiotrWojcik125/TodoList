package todoList.hello;

import todoList.lang.Lang;
import todoList.lang.LangRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private static final String FALLBACK_ID_WELCOME = "Hola";
    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallback() throws Exception {
        // given
        LangRepository mockRepository = alwaysReturningHelloRepository();
        HelloService SUT = new HelloService(mockRepository);
        //when
        String result = SUT.prepareGreeting(null, -1);
        //then
        assertEquals(WELCOME + " "+ HelloService.FALLBACK_NAME + "!",result);
    }
    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() throws Exception {
        // given
        LangRepository mockRepository = alwaysReturningHelloRepository();
        HelloService SUT = new HelloService(mockRepository);
        String name = "test";

        //when
        String result = SUT.prepareGreeting(name,-1);

        //then
        assertEquals(WELCOME +" "+ name + "!",result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdlang() throws Exception {
        // given
        LangRepository mockRepository = fallbackIdRepository();
        HelloService SUT = new HelloService(mockRepository);

        //when
        String result = SUT.prepareGreeting(null,null);

        //then
        assertEquals(FALLBACK_ID_WELCOME +" "+ HelloService.FALLBACK_NAME + "!",result);

    }
    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() throws Exception{
        //given
        LangRepository mockRepository = new LangRepository(){
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
        HelloService SUT = new HelloService(mockRepository);
        //when
        String result = SUT.prepareGreeting(null,null);

        //then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() +" "+ HelloService.FALLBACK_NAME + "!",result);

    }
    private LangRepository fallbackIdRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }
    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
}
