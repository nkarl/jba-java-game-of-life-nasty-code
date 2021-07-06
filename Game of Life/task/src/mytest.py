import random

class Life:
    def __init__(self, size: int, seed: int):
        random.seed(seed)
        self.map = []
        for i in range(size):
            temp = []
            for j in range(size):
                temp.append(random.randint(0, 1))
            self.map.append(temp)
        self.size = len(self.map)


    def propagate(self, gens: int):
        if (gens > 0):
            for i in range(0, gens):
                self.generate()
                self.view()


    def generate(self):
        for i in range(0, self.size):
            for j in range(0, self.size):
                self.map[i][j] = 1 if self.alive(i, j) else 0


    def alive(self, row: int, col: int):
        neighbors = 0
        for i in range(-1, 2):
            r = self.wrap(i)
            for j in range(-1, 2):
                c = self.wrap(j)
                if r == row and c == col:
                    continue
                neighbors += 1 if self.map[i][j] == 1 else 0
                if neighbors > 3:
                    break
        return 1 if neighbors == 2 or neighbors == 3 else 0


    def wrap(self, c: int):
        return (c + self.size) % self.size


    def view(self):
        view = ""
        for row in self.map:
            for cell in row:
                view += 'O' if cell == 1 else ' '
            view += '\n'
        print(view)
