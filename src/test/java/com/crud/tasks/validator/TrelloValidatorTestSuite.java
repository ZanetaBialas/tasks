package com.crud.tasks.validator;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TrelloValidatorTestSuite {

    @Test
    void validateCardTest() {
        //Given
        Logger logger = (Logger) LoggerFactory.getLogger(TrelloFacade.class);

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        logger.addAppender(listAppender);

        TrelloCard card = new TrelloCard("test", "Dsc", "top", "1234");

        //When
        TrelloValidator validator = new TrelloValidator();
        validator.validateCard(card);
        List<ILoggingEvent> logsList = listAppender.list;

        //Then
        assertEquals("Someone is testing my application!", logsList.get(0).getMessage());
        assertEquals(Level.INFO, logsList.get(0).getLevel());
    }

    @Test
    void validateTrelloBoardsTest() {
        //Given
        Logger logger = (Logger) LoggerFactory.getLogger(TrelloFacade.class);

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        logger.addAppender(listAppender);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1234", "Name", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("1114", "test", new ArrayList<>()));

        //When
        TrelloValidator validator = new TrelloValidator();
        validator.validateTrelloBoards(trelloBoards);
        List<ILoggingEvent> logsList = listAppender.list;

        //Then
        assertEquals("Boards have been filtered. Current list size: 1", logsList.get(1).getMessage());
        assertEquals(Level.INFO, logsList.get(1).getLevel());
    }
}