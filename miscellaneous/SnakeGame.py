# Reference: https://www.edureka.co/blog/snake-game-with-pygame/#building
import time
import pygame

white = (255, 255, 255)
black = (0, 0, 0)
red = (255, 0, 0)

pygame.init()
pygame.display.set_caption("Snake game")

game_display_width = 800
game_display_height = 600
game_display = pygame.display.set_mode((game_display_width, game_display_height))
coordinate_x = game_display_width / 2
coordinate_y = game_display_height / 2
game_over = False
clock = pygame.time.Clock()
snake_velocity = 15
action_used_x = 0
action_used_y = 0
font_style = pygame.font.SysFont(None, 50)


def message(msg, color):
    mesg = font_style.render(msg, True, color)
    game_display.blit(
        mesg, [(game_display_width / 2) - 90, (game_display_height / 2) - 90]
    )


while not game_over:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            game_over = True
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_UP or event.key == pygame.K_w:
                action_used_x -= 0
                action_used_y = -10
            elif event.key == pygame.K_DOWN or event.key == pygame.K_s:
                action_used_x -= 0
                action_used_y = 10
            elif event.key == pygame.K_RIGHT or event.key == pygame.K_d:
                action_used_x -= -10
                action_used_y = 0
            elif event.key == pygame.K_LEFT or event.key == pygame.K_a:
                action_used_x -= +10
                action_used_y = 0

    coordinate_x += action_used_x
    coordinate_y += action_used_y

    if (
        coordinate_x >= game_display_width
        or coordinate_x < 0
        or coordinate_y >= game_display_height
        or coordinate_y < 0
    ):
        game_over = True

    game_display.fill(white)
    pygame.draw.rect(game_display, black, [coordinate_x, coordinate_y, 10, 10])

    pygame.display.update()
    clock.tick(snake_velocity)

message("GAME OVER", red)
pygame.display.update()
time.sleep(2)
pygame.quit()
quit()
