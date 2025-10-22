from client.client import get_associations, get_presidents

if __name__ == "__main__":
    ids = [6, 5, 8, 10]

    print("Asociaciones:")
    asociaciones = get_associations(ids)
    print(asociaciones)

    print("\nPresidentes:")
    presidentes = get_presidents(ids)
    print(presidentes)