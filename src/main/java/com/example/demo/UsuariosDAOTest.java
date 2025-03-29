package com.example.demo;

import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UsuariosDAOTest {

    private JdbcTemplate jdbcTemplate;

    public UsuariosDAOTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UsuariosDAOTest() {
        this.jdbcTemplate = new JdbcTemplate();
    }

    /*public  ArrayList<UsuarioDTO> leeUsuarios(){
        ArrayList<UsuarioDTO> lista = new ArrayList();
        UsuarioDTO usuario1 = new UsuarioDTO("eldanyga", "eldanyga123");
        UsuarioDTO usuario2 = new UsuarioDTO("admin", "admin");
        UsuarioDTO usuario3 = new UsuarioDTO("pepito", "teamolola");
        lista.add(usuario1);
        lista.add(usuario2);
        lista.add(usuario3);


        return lista;
    }  */

    public boolean insertaUsuario(Usuario usuario){
        String sql = "insert into usuarios(usuario, nombre, apellidos, email, contraseña) values(?,?,?,?,?);";
        this.jdbcTemplate.update(sql,  usuario.getNombre(), usuario.getApellidos(), usuario.getUsuario(), usuario.getEmail(), usuario.getPassword());
        return true;
    }

    public List<Usuario> leeUsuarios() {
        String sql = "SELECT id, nombre, apellidos, usuario, email, contraseña FROM usuarios";
        return jdbcTemplate.query(sql, usuarioRowMapper);
    }



    private final RowMapper<Usuario> usuarioRowMapper = (rs, rowNum) ->
            new Usuario(

                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("usuario"),
                    rs.getString("email"),
                    rs.getString("contraseña")  // Mapea la contraseña
            );





}
