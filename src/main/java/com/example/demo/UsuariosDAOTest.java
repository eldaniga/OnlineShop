package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UsuariosDAOTest {

    private JdbcTemplate jdbcTemplate;

    public UsuariosDAOTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        String sql = "insert into usuarios( nombre, apellidos, alias, email, contraseña) values(?,?,?,?,?);";
        this.jdbcTemplate.update(sql,  usuario.getNombre(), usuario.getApellidos(), usuario.getAlias(), usuario.getEmail(), usuario.getPassword());

        String sql2 = "insert into roles(alias, rol) values (?,?)";
        this.jdbcTemplate.update(sql2, usuario.getAlias(), usuario.getRole());
        return true;
    }

    public List<Usuario> leeUsuarios() {
        String sql = " SELECT u.nombre, u.apellidos, u.alias, u.email, u.contraseña, r.rol FROM usuarios u LEFT JOIN roles r ON u.alias = r.alias;";


        return jdbcTemplate.query(sql, usuarioRowMapper);
    }

    public Usuario findByUsername(String username) {
        String sql = "SELECT * FROM usuarios WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, usuarioRowMapper, username);
    }
    public Usuario findRoleByUsername(String username) {
        String sql = "SELECT u.*, r.rol FROM usuarios u " +
                "LEFT JOIN roles r ON u.alias = r.alias " +
                "WHERE u.alias = ?";
        return jdbcTemplate.queryForObject(sql,usuarioRowMapper, username);


    }

    public int eliminarUsuario(String alias){
        String sqlDelete = "DELETE FROM usuarios WHERE alias=alias;";
        return jdbcTemplate.update(sqlDelete);

    }


    private final RowMapper<Usuario> usuarioRowMapper = (rs, rowNum) ->
            new Usuario(
                    rs.getString("alias"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),

                    rs.getString("email"),
                    rs.getString("contraseña"),
                    rs.getString("rol")
            );





}
