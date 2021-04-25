using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MyPrikormWebAPI.Interfaces
{
    interface IdbRepository
    {
        //Unit of Work -- паттерн
        IRepositoryUser Users { get; }
        IRepositoryProduct Products { get; }
        IRepositoryPrikormList PrikormLists { get; }
        IRepositoryMeal Meals { get; }
        int Save();
    }
}
