package com.xdclass.demo;

import com.xdclass.demo.model.entity.User;
import com.xdclass.demo.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	public void testGeneJwt(){


		User user = new User();
		user.setId(9);
		user.setName("要吃不吃茶泡饭");
		user.setHeadImg("png");

		String token = JWTUtils.geneJsonWebToken(user);

		System.out.println(token);

		Claims claims = JWTUtils.checkJWT(token);

		System.out.println(claims.get("name"));

	}

}
