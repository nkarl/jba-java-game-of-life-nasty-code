import random

class LifeCore:
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
        print("GEN 0:")
        self.view()
        for i in range(0, gens):
            self.generate()
            print(f"GEN {i}:")
            self.view()


    def generate(self):
        # self.view()
        for i in range(0, self.size):
            for j in range(0, self.size):
                # self.view_local(i, j)
                self.map[i][j] = 1 if self.live(i, j) is True else 0
            # self.view()
        # self.view()


    def live(self, row: int, col: int):
        count = self.checkSurround(row, col)
        return count == 2 or count == 3
    
    def checkSurround(self, row: int, col: int):
        k = 0
        for i in range(-1, 2):
            r = self.__wrap(row + i)
            for j in range(-1, 2):
                c = self.__wrap(col + j)
                if r == row and c == col:
                    continue
                cell = self.map[r][c]
                k += 1 if cell == 1 else 0
        return k

    def checkNeighbors(self, row: int, col: int):
        k = 0
        mm0 = []
        for i in range(-1, 2):
            r = self.__wrap(row + i)
            mm1 = []
            for j in range(-1, 2):
                c = self.__wrap(col + j)
                cell = self.map[r][c]
                ch = 'O' if cell == 1 else '-'
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
        # self.view()
        print(f"\n\tThe neighborhood map of the cell @(col={c}, row={r}):\n")
        print(view)


    def __wrap(self, c: int):
        return (c + self.size) % self.size


    def view(self):
        view = "\t\t  | "
        for i in range(0, len(self.map)):
            i = i % 10
            view += f"{i} "
        print(view)
        view = "\t\t----"
        for i in range(0, len(self.map)):
            view += "--"
        print(view)
        view = "\t\t"
        for i in range(len(self.map)):
            i = i % 10
            view += f"{i} | "
            for j in range(len(self.map)):
                view += f'{self.map[i][j]}' # if self.map[i][j] == 1 else ' '
                view += ' '
            view += '\n\t\t'
        print(view)

    def view_local(self, r: int, c: int):
        print("\t----------------------------------------------------")
        print(f"\tRAW @ ({r}, {c}):")
        print("\t----------------------------------------------------\n")
        self.view()


class Life(LifeCore):
    def superview(self):
        super().view();

    def view(self):
        view = "\t\t  | "
        for i in range(0, len(self.map)):
            i = i % 10
            view += f"{i} "
        print(view)
        view = "\t\t----"
        for i in range(0, len(self.map)):
            view += "--"
        print(view)
        view = "\t\t"
        for i in range(len(self.map)):
            i = i % 10
            view += f"{i} | "
            for j in range(len(self.map)):
                view += 'O' if self.map[i][j] == 1 else '-'
                view += ' '
            view += '\n\t\t'
        print(view)

    def view_local(self, r: int, c: int):
        print("\t----------------------------------------------------")
        print(f"\tRAW @ (c={c}, r={r}):")
        print("\t----------------------------------------------------\n")
        self.view()
        pass

if __name__ == "__main__":
    # m = Life(15,4)
    # m = Life(10, 4)
    # m.view()
    # m.rawview()
    # a = m.checkNeighbors(0,1)
    # m.viewNeighbors(a)
    # b = m.checkNeighbors(3,5)
    # m.viewNeighbors(b)
    # c = m.checkNeighbors(7,8)
    # m.viewNeighbors(c)
    m = Life(5, 1)
    # m.rawview()
    m.propagate(10)
    # m.rawview()
