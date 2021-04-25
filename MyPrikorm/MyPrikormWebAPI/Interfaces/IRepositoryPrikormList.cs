using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MyPrikormWebAPI.Model.DB.Entities;
using MyPrikormWebAPI.Model.DB.Context;

namespace MyPrikormWebAPI.Interfaces
{
    interface IRepositoryPrikormList
    {
        Task<List<PrikormList>> GetAll();
        Task<PrikormList> Get(int id);
        PrikormList Create(PrikormList prikormlist);
        PrikormList Update(PrikormList prikormlist);
        PrikormList Delete(int id);
    }
}
