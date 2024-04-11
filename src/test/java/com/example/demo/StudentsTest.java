//package com.example.demo;
//
//
//
//
//import java.nio.charset.Charset;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.web.servlet.mvc.Controller;
//
//import com.example.demo.controllers.StudentController;
//import com.example.demo.repository.StudentRepo;
//
////import org.springframework.test.context.junit4.SpringRunner;
//
//
//
//@SpringBootTest
//@AutoConfigureMockMvc 
//public class StudentsTest {
//
//    @MockBean
//    private StudentRepo userRepository;
//    
//    @Autowired
//    private StudentController userController;
//
//    @Autowired
//    private MockMvc mockMvc;
//    
//    
////    @Test
////    public void whenGetRequestToHome_thenCorrectResponse() throws Exception {
////        mockMvc.perform(MockMvcRequestBuilders.get("/"))
////          .andExpect(MockMvcResultMatchers.status().isOk());
//////          .andExpect(MockMvcResultMatchers.content().string("<h1>Hello World</h1>"));
////    }
//    
//    @Test
//    public void whenGetRequestToGetAllDataFromPersonDB_thenCorrectResponse() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/students/list"))
//          .andExpect(MockMvcResultMatchers.status().isOk());
//    }
////    
////    @Test
////    public void whenPostRequestToStudentsAndValidStudent_thenCorrectResponse() throws Exception {
////        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
////        String student = "{\"first_name\": \"bob\", \"last_name\" : \"smith\", \"age\" : 20.6}";
////        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
////          .content(student)
////          .contentType(MediaType.APPLICATION_JSON))
////          .andExpect(MockMvcResultMatchers.status().isCreated());
//////          .andExpect(MockMvcResultMatchers.content()
//////            .contentType(textPlainUtf8));
////    }
////
////    
////    
////// first name validatiuon .....
////    
////    @Test
////    public void whenPostRequestToUsersAndInvalidFirstName_thenCorrectResponse() throws Exception {
////        String user = "{\"first_name\": \"\", \"last_name\" : \"Smith\", \"age\" : 25.1}";
////        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
////          .content(user)
////          .contentType(MediaType.APPLICATION_JSON))
////          .andExpect(MockMvcResultMatchers.status().isBadRequest());
////    }
////
////    @Test
////    public void whenPostRequestToUsersAndInvalidFirstNameLength_thenCorrectResponse() throws Exception {
////        String user = "{\"first_name\": \"JohnJohnJohn\", \"last_name\" : \"Smith\", \"age\" : 25.5}";
////        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
////          .content(user)
////          .contentType(MediaType.APPLICATION_JSON))
////          .andExpect(MockMvcResultMatchers.status().isBadRequest());
////    }
////
////    @Test
////    public void whenPostRequestToUsersAndInvalidFirstNameCharacters_thenCorrectResponse() throws Exception {
////        String user = "{\"first_name\": \"John@\", \"last_name\" : \"Smith\", \"age\" : 0.6}";
////        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
////          .content(user)
////          .contentType(MediaType.APPLICATION_JSON))
////          .andExpect(MockMvcResultMatchers.status().isBadRequest());
////    }
////
////    
////// age validation  ......
//////    @Test
//////    public void whenPostRequestToUsersAndInvalidAge_thenCorrectResponse() throws Exception {
//////        String user = "{\"first_name\": \"Johnhhhh\", \"last_name\" : \"Smithhh\", \"age\" : -5}";
//////        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
//////          .content(user)
//////          .contentType(MediaType.APPLICATION_JSON))
//////          .andExpect(MockMvcResultMatchers.status().isBadRequest());
//////    }
////    
//    
//
//
//    
//}
