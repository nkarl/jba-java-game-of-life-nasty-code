import random

class Life:
    """class constructor takes two params.

        :param size: size of the cell map
        :param seed: the seeded value for randomization
    """
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
            for i in range(1, gens):
                self.generate()
                self.view()


    def generate(self):
        for i in range(0, self.size):
            for j in range(0, self.size):
                self.map[i][j] = 1 if self.__alive(i, j) else 0


    def __alive(self, row: int, col: int):
        neighbors = 0
        for i in range(-1, 2):
            r = self.__wrap(i)
            for j in range(-1, 2):
                c = self.__wrap(j)
                if r == row and c == col:
                    continue
                neighbors += 1 if self.map[i][j] == 1 else 0
                if neighbors > 3:
                    break
        return 1 if neighbors == 2 or neighbors == 3 else 0

    def checkNeighbors(self, row: int, col: int):
        k = 0
        mm0 = []
        for i in range(-1, 2):
            r = self.__wrap(row + i)
            mm1 = []
            for j in range(-1, 2):
                c = self.__wrap(col + j)
                ch = 'O' if self.map[r][c] == 1 else '-'
                if r == row and c == col:
                    ch = '*'
                mm1 += [ch]
                k += 1
            mm0 += [mm1]
        return ((row, col), mm0)

    def viewNeighbors(self, rcmm):
        rc, mm = rcmm
        r, c = rc
        view = "\t"
        for row in mm:
            view += "\t"
            for cell in row: 
                view += cell
                view += ' '
            view += '\n\t'
        self.view()
        print(f"\n\tThe neighborhood map of the cell @(col={c}, row={r}):\n")
        print(view)


    def __wrap(self, c: int):
        return (c + self.size) % self.size


    def view(self):
        print("\n\t----------------------------------------------------")
        print("\tThe Universe Map:")
        print("\t----------------------------------------------------\n")
        view = "\t\t  | "
        for i in range(0, len(self.map)):
            view += f"{i} "
        print(view)
        view = "\t\t----"
        for i in range(0, len(self.map)):
            view += "--"
        print(view)
        view = "\t\t"
        for i in range(len(self.map)):
            view += f"{i} | "
            for j in range(len(self.map)):
                view += 'O' if self.map[i][j] == 1 else '-'
                view += ' '
            view += '\n\t\t'
        print(view)


    # def view(self):
        # view = "\t"
        # for row in self.map:
            # view += "\t"
            # for cell in row:
                # view += 'O' if cell == 1 else '-'
                # view += ' '
            # view += '\n\t'
        # print("\nThe Universe Map:\n")
        # print(view)


if __name__ == "__main__":
    m = Life(3,4)
    a = m.checkNeighbors(0,1)
    a = m.viewNeighbors(a)
