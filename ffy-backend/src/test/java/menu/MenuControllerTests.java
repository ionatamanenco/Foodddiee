package menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import ia.ffy.foodforyou.menu.MenuController;
import ia.ffy.foodforyou.menu.MenuDTO;
import ia.ffy.foodforyou.menu.MenuService;
import lombok.AllArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MenuController.class)
@AllArgsConstructor
class MenuControllerTests {

    private MockMvc mockMvc;

    private MenuService menuService;

    private MenuDTO menuDTO;

    @BeforeEach
    void setUp() {
        menuDTO = new MenuDTO();
        menuDTO.setId("1");
        menuDTO.setDescription("Description 1");
        menuDTO.setPrice(10.0);
    }

    @Test
    void getAllMenus_ReturnsListOfMenus() throws Exception {
        List<MenuDTO> menuList = new ArrayList<>();
        menuList.add(menuDTO);
        given(menuService.findAll()).willReturn(menuList);

        mockMvc.perform(get("/api/menus"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.is(menuDTO.getId())))
                .andExpect(jsonPath("$[0].description", Matchers.is(menuDTO.getDescription())))
                .andExpect(jsonPath("$[0].price", Matchers.is(menuDTO.getPrice())));
    }

    @Test
    void getMenu_WithValidId_ReturnsMenu() throws Exception {
        given(menuService.get(menuDTO.getId())).willReturn(menuDTO);

        mockMvc.perform(get("/api/menus/{id}", menuDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(menuDTO.getId())))
                .andExpect(jsonPath("$.description", Matchers.is(menuDTO.getDescription())))
                .andExpect(jsonPath("$.price", Matchers.is(menuDTO.getPrice())));
    }

    @Test
    void createMenu_WithValidMenu_ReturnsCreatedStatus() throws Exception {
        given(menuService.create(menuDTO)).willReturn(menuDTO.getId());

        mockMvc.perform(post("/api/menus")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(menuDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/api/menus/")));
    }

    @Test
    void updateMenu_WithValidIdAndMenu_ReturnsOkStatus() throws Exception {
        mockMvc.perform(put("/api/menus/{id}", menuDTO.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(menuDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMenu_WithValidId_ReturnsNoContentStatus() throws Exception {
        mockMvc.perform(delete("/api/menus/{id}", menuDTO.getId()))
                .andExpect(status().isNoContent());
    }

    // Helper method to convert Java objects to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
