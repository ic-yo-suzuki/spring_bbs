package test.bbs.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bbs.entity.NgWord;
import bbs.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-application-config.xml"})
public class TopControllerTest {
	@Autowired
	MessageService messageService;

	@Test
	public void NGワードを読み込んだときにリスト内の状態にかかわらずNullを返さない(){
		List<NgWord> actual = messageService.getNgWord();
		assertThat(actual, is(not(null)));
	}
}
