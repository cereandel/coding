# Reference: https://www.edureka.co/blog/snake-game-with-pygame/#building
import random
import pygame

pygame.init()
pygame.display.set_caption("Snake game")
game_display_width = 800
game_display_height = 600
game_display = pygame.display.set_mode((game_display_width, game_display_height))

clock = pygame.time.Clock()
snake_dimentions = 10
snake_velocity = 12
food_coordinate_x = food_coordinate_y = 0

white = (255, 255, 255)
yellow = (255, 255, 102)
black = (0, 0, 0)
red = (213, 50, 80)
green = (0, 255, 0)
blue = (50, 153, 213)

font_style = pygame.font.SysFont(None, 50)


def game_over_message(msg1, msg2, msg3, color1, color2):
    mesg = font_style.render(msg1, True, color1)
    game_display.blit(
        mesg, [(game_display_width / 2) - 125, (game_display_height / 2) - 80]
    )
    mesg = font_style.render(msg2, True, color2)
    game_display.blit(
        mesg, [(game_display_width / 2) - 150, (game_display_height / 2) - 30]
    )
    mesg = font_style.render(msg3, True, color2)
    game_display.blit(
        mesg, [(game_display_width / 2) - 65, (game_display_height / 2) + 10]
    )


def generate_food():
    global food_coordinate_x, food_coordinate_y
    food_coordinate_x = (
        round(
            random.randrange(snake_dimentions, game_display_width - snake_dimentions)
            / 10.0
        )
        * 10.0
    )
    food_coordinate_y = (
        round(
            random.randrange(snake_dimentions, game_display_height - snake_dimentions)
            / 10.0
        )
        * 10.0
    )


def gameLoop():
    global snake_velocity
    game_over = False
    game_closed = False

    action_used_x = 0
    action_used_y = 0

    snake_coordinate_x = game_display_width / 2
    snake_coordinate_y = game_display_height / 2

    generate_food()

    while not game_over:
        while game_closed == True:
            game_display.fill(white)
            game_over_message(
                "GAME OVER!", "Jugar de Nuevo (1)", "Salir(2)", red, black
            )
            pygame.display.update()
            for event in pygame.event.get():
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_2:
                        game_over = True
                        game_closed = False
                    if event.key == pygame.K_1:
                        gameLoop()
                elif event.type == pygame.QUIT:
                    quit()

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                quit()
            if event.type == pygame.KEYDOWN:
                if (
                    event.key == pygame.K_UP or event.key == pygame.K_w
                ) and action_used_y == 0:
                    action_used_x = 0
                    action_used_y = -snake_dimentions
                elif (
                    event.key == pygame.K_DOWN or event.key == pygame.K_s
                ) and action_used_y == 0:
                    action_used_x = 0
                    action_used_y = snake_dimentions
                elif (
                    event.key == pygame.K_RIGHT or event.key == pygame.K_d
                ) and action_used_x == 0:
                    action_used_x = snake_dimentions
                    action_used_y = 0
                elif (
                    event.key == pygame.K_LEFT or event.key == pygame.K_a
                ) and action_used_x == 0:
                    action_used_x = -snake_dimentions
                    action_used_y = 0

        snake_coordinate_x += action_used_x
        snake_coordinate_y += action_used_y

        if (
            snake_coordinate_x >= game_display_width
            or snake_coordinate_x < 0
            or snake_coordinate_y >= game_display_height
            or snake_coordinate_y < 0
        ):
            game_closed = True

        game_display.fill(white)
        pygame.draw.rect(
            game_display,
            black,
            [
                snake_coordinate_x,
                snake_coordinate_y,
                snake_dimentions,
                snake_dimentions,
            ],
        )
        pygame.draw.rect(
            game_display,
            blue,
            [food_coordinate_x, food_coordinate_y, snake_dimentions, snake_dimentions],
        )

        pygame.display.update()

        if (
            snake_coordinate_x == food_coordinate_x
            and snake_coordinate_y == food_coordinate_y
        ):
            generate_food()
            snake_velocity += 1

        clock.tick(snake_velocity)


gameLoop()
pygame.quit()
quit()
